package cl.speedfast.database;

import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.TipoPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {
    public void guardar(String direccion, TipoPedido tipoPedido, EstadoPedido estadoPedido){
        // crea un pedido
        String sql = "INSERT INTO pedido VALUES (null, ?,?,?)";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, direccion);
            stmt.setString(2, tipoPedido.name());
            stmt.setString(3, estadoPedido.name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Pedido> obtenerPedidos(){
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado from pedido ORDER BY id";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String direccion = rs.getString("direccion");
                    String tipo = rs.getString("tipo");
                    String estado = rs.getString("estado");
                    Pedido pedido = new Pedido(id, direccion, EstadoPedido.find(estado), TipoPedido.find(tipo));
                    pedidos.add(pedido);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedidos;
    }

    public void updateStatus(Pedido pedido, EstadoPedido estadoPedido){
        String sql = "UPDATE pedido SET estado = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, estadoPedido.name());
            stmt.setInt(2, pedido.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
