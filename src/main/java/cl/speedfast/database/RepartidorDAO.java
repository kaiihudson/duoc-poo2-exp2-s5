package cl.speedfast.database;

import cl.speedfast.model.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepartidorDAO {
    public List<Repartidor> listarTodos(){
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

    public void crearRepartidor(Repartidor repartidor){
        String sql = "INSERT INTO repartidor VALUES (null, ?)";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, repartidor.getNombre());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
