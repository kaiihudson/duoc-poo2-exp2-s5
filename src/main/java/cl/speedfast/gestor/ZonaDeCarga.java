package cl.speedfast.gestor;

import cl.speedfast.exceptions.EstadoIncorrectoException;
import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;

import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ZonaDeCarga {
    BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    public BlockingQueue<Pedido> getPedidosPendientes(){
        return pedidosPendientes;
    }

    public synchronized void agregarPedido(Pedido p){
        pedidosPendientes.add(p);
    }
    public synchronized void retirarPedido() throws InterruptedException {
        if (pedidosPendientes.isEmpty()){
            return;
        }
        Pedido p = pedidosPendientes.take();
        int delay = repartirPedido(p);
        Thread.sleep(delay);
        System.out.println("Este pedido tomo: " + delay/1000 + " segundos.");
        entregarPedido(p);
    }

    public int repartirPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.PENDIENTE){
            p.setEstado(EstadoPedido.EN_REPARTO);
            return new Random().nextInt(15000);
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }

    public void entregarPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.EN_REPARTO) {
            p.setEstado(EstadoPedido.ENTREGADO);
            System.out.println(p.getId() + " Entregado");
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }
}
