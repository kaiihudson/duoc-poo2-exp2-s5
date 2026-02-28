package cl.speedfast.gestor;

import cl.speedfast.database.EntregaDAO;
import cl.speedfast.database.PedidoDAO;
import cl.speedfast.database.RepartidorDAO;
import cl.speedfast.exceptions.EstadoIncorrectoException;
import cl.speedfast.model.Entrega;
import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.Repartidor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class ZonaDeCarga {
    PedidoDAO pedidoDAO = new PedidoDAO();
    RepartidorDAO repartidorDAO = new RepartidorDAO();
    EntregaDAO entregaDAO = new EntregaDAO();
    BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();
    List<Repartidor> repartidores = new ArrayList<>();

    public BlockingQueue<Pedido> getPedidosPendientes(){
        updateListaPedidos();
        return pedidosPendientes;
    }
    public List<Repartidor> getRepartidores(){
        updateListaRepartidores();
        return repartidores;
    }

    public void updateListaPedidos(){
        List<Pedido> listaPedidosPendientes = pedidoDAO.findAllPedidos();
        pedidosPendientes.clear();
        for(Pedido pedido : listaPedidosPendientes){
            if (pedido.getEstado() == EstadoPedido.PENDIENTE){
                pedidosPendientes.add(pedido);
            }
        }
    }

    public void crearPedido(Pedido pedido){
        pedidoDAO.createPedido(pedido);
    }

    public synchronized void agregarPedido(Pedido p){
        pedidosPendientes.add(p);
        updateListaPedidos();
    }

    public synchronized void updatePedido(Pedido p, EstadoPedido estado){
        p.setEstado(estado);
        pedidoDAO.updatePedido(p);
    }

    public void crearRepartidor(String nombreRepartidor){
        Repartidor r = new Repartidor(nombreRepartidor);
        repartidorDAO.createRepartidor(r);
        updateListaRepartidores();
    }

    public void updateListaRepartidores(){
        List<Repartidor> listaRepartidores = repartidorDAO.getRepartidores();
        for(Repartidor repartidor : listaRepartidores){
            repartidor.setZonaDeCarga(this);
        }
        repartidores.clear();
        repartidores.addAll(listaRepartidores);
    }

    public synchronized Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }
    public void logPedido(Pedido p, Repartidor r) throws SQLException {
        Entrega entrega = new Entrega(p.getId(), r.getId());
        entregaDAO.crearEntrega(entrega);
    }

    public void executeAll(){
        if (!pedidosPendientes.isEmpty()){
            ExecutorService es = Executors.newFixedThreadPool(3);
            for(Repartidor repartidor : repartidores){
                es.execute(repartidor);
            }
            es.shutdown();
            try {
                if (es.awaitTermination(30, TimeUnit.SECONDS)) {
                    System.out.println("Stopping Gracefully");
                    es.shutdownNow();
                }
            } catch (InterruptedException e ){
                es.shutdownNow();
            }
        }
    }

    public int repartirPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.PENDIENTE){
            p.setEstado(EstadoPedido.EN_REPARTO);
            updatePedido(p, EstadoPedido.EN_REPARTO);
            System.out.println("Pedido: " + p.getId() + " recepcionado para entrega");
            return new Random().nextInt(5000);
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }

    public void entregarPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.EN_REPARTO) {
            p.setEstado(EstadoPedido.ENTREGADO);
            updatePedido(p, EstadoPedido.ENTREGADO);
            System.out.println("Pedido: " + p.getId() + " entregado");
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }
}
