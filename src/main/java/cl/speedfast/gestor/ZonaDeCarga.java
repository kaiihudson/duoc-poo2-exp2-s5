package cl.speedfast.gestor;

import cl.speedfast.exceptions.EstadoIncorrectoException;
import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ZonaDeCarga {
    BlockingQueue<Pedido> pedidosPendientes = new LinkedBlockingQueue<>();

    public BlockingQueue<Pedido> getPedidosPendientes(){
        return pedidosPendientes;
    }

    public synchronized void agregarPedido(Pedido p){
        pedidosPendientes.add(p);
    }
    public synchronized Pedido retirarPedido() {
        return pedidosPendientes.poll();
    }

    public int repartirPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.PENDIENTE){
            p.setEstado(EstadoPedido.EN_REPARTO);
            System.out.println("Pedido: " + p.getId() + " recepcionado para entrega");
            return new Random().nextInt(5000);
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }

    public void entregarPedido(Pedido p) throws EstadoIncorrectoException {
        if (p.getEstado() == EstadoPedido.EN_REPARTO) {
            p.setEstado(EstadoPedido.ENTREGADO);
            System.out.println("Pedido: " + p.getId() + " entregado");
        } else {
            throw new EstadoIncorrectoException("Estado incorrecto: " + p.getEstado());
        }
    }
}
