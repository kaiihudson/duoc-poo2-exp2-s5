package cl.speedfast.database;

import cl.speedfast.database.interfaces.RepartidorCRUD;
import cl.speedfast.model.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO implements RepartidorCRUD {

    @Override
    public Repartidor getRepartidor(int id) {
        String sql = "SELECT * FROM pedido WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()){
                while(rs.next()){
                    return new Repartidor(
                            rs.getInt("id"),
                            rs.getString("nombre")
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Repartidor> getRepartidores() {
        List<Repartidor> repartidores = new ArrayList<>();
        // obtiene todos los repartidores
        String sql = "SELECT id,nombre FROM repartidor";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    repartidores.add(new Repartidor(rs.getInt("id"),rs.getString("nombre")));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return repartidores;
    }

    @Override
    public void createRepartidor(Repartidor repartidor) {
        String sql = "INSERT INTO repartidor VALUES (null, ?)";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, repartidor.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateRepartidor(Repartidor repartidor) {
        String sql = "UPDATE repartidor SET nombre = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, repartidor.getNombre());
            stmt.setInt(2, repartidor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteRepartidor(int id) {
        String sql = "DELETE FROM repartidor WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
