package cl.speedfast.database;

import cl.speedfast.database.interfaces.EntregaCRUD;
import cl.speedfast.model.Entrega;

import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntregaDAO implements EntregaCRUD {

    private void setAttributes(Entrega entrega, int id, int idPedido, int idRepartidor, java.sql.Date fecha, Time hora) {
        entrega.setIdEntrega(id);
        entrega.setIdPedido(idPedido);
        entrega.setIdRepartidor(idRepartidor);
        entrega.setFechaEntrega(fecha);
        entrega.setHoraEntrega(hora);
    }

    @Override
    public void crearEntrega(Entrega entrega) {
        int idPedido = entrega.getIdPedido();
        int idRepartidor = entrega.getIdRepartidor();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        LocalTime time = LocalTime.now();

        String sql = "INSERT INTO entrega VALUES (null, ?, ?, ?, ? )";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idPedido);
            stmt.setInt(2, idRepartidor);
            stmt.setDate(3, fecha);
            stmt.setTime(4, Time.valueOf(time));
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Entrega getById(int id) {
        Entrega entrega = new Entrega();
        String sql = "SELECT * FROM entrega WHERE id = ?";
        try(Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            try (ResultSet rs = stmt.executeQuery()) {
                stmt.setInt(1, id);
                while (rs.next()) {
                    setAttributes(
                            entrega,
                            rs.getInt("id"),
                            rs.getInt("id_pedido"),
                            rs.getInt("id_repartidor"),
                            rs.getDate("fecha"),
                            rs.getTime("hora")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entrega;
    }

    @Override
    public List<Entrega> getAllEntregas() {
        List<Entrega> entregas = new ArrayList<>();
        String sql = "SELECT * FROM entrega";
        try(Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Entrega entrega = new Entrega();
                    setAttributes(
                            entrega,
                            rs.getInt("id"),
                            rs.getInt("id_pedido"),
                            rs.getInt("id_repartidor"),
                            rs.getDate("fecha"),
                            rs.getTime("hora")
                    );
                    entregas.add(entrega);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entregas;
    }

    @Override
    public void eliminarEntrega(int id) {
        String sql = "DELETE FROM entrega WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void actualizarEntrega(Entrega entrega) {
        String sql = "update entrega set id_pedido=?, id_repartidor=?, fecha=?, hora=? where id=?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, entrega.getIdPedido());
            stmt.setInt(2, entrega.getIdRepartidor());
            stmt.setDate(3, entrega.getFechaEntrega());
            stmt.setTime(4, entrega.getHoraEntrega());
            stmt.setInt(5, entrega.getIdEntrega());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
