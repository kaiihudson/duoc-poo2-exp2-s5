package cl.speedfast.database.interfaces;

import cl.speedfast.model.Pedido;

import java.util.List;

public interface PedidoCRUD {
     void createPedido(Pedido pedido);
     Pedido findPedido(int id);
     List<Pedido> findAllPedidos();
     void updatePedido(Pedido pedido);
     void deletePedido(int id);
}
