package cl.speedfast.database;

import cl.speedfast.database.interfaces.PedidoCRUD;
import cl.speedfast.model.EstadoPedido;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.TipoPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO implements PedidoCRUD {

    @Override
    public void createPedido(Pedido pedido) {
        String sql = "INSERT INTO pedido VALUES (null, ?,?,?)";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipo().name());
            stmt.setString(3, pedido.getEstado().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pedido findPedido(int id) {
        Pedido pedido = new Pedido();
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                pedido.setId(rs.getInt("id"));
                pedido.setDireccionEntrega(rs.getString("direccion"));
                pedido.setTipo(TipoPedido.valueOf(rs.getString("tipo")));
                pedido.setEstado(EstadoPedido.valueOf(rs.getString("estado")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pedido;
    }

    @Override
    public List<Pedido> findAllPedidos() {
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
    public List<Pedido> findAllPedidosPendientes() {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT id, direccion, tipo, estado from pedido where estado = 'PENDIENTE' ORDER BY id";
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

    @Override
    public void updatePedido(Pedido pedido) {
        String sql = "update pedido set direccion = ?, tipo = ?, estado = ? where id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, pedido.getDireccionEntrega());
            stmt.setString(2, pedido.getTipo().name());
            stmt.setString(3, pedido.getEstado().name());
            stmt.setInt(4, pedido.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deletePedido(int id) {
        String sql =  "DELETE FROM pedido WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
