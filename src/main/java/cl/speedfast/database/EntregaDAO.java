package cl.speedfast.database;

import cl.speedfast.model.Pedido;
import cl.speedfast.model.Repartidor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

public class EntregaDAO {
    public void guardar(Pedido pedido, Repartidor repartidor) throws SQLException {
        int idPedido = pedido.getId();
        int idRepartidor = repartidor.getId();
        java.sql.Date fecha = new java.sql.Date(new Date().getTime());
        LocalTime time = LocalTime.now();

        String sql = "INSERT INTO entrega VALUES (null, ?, ?, ?, ? )";
        try (Connection conn = ConexionDB.getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setInt(1, idPedido);
            stmt.setInt(2, idRepartidor);
            stmt.setDate(3, fecha);
            stmt.setTime(4, Time.valueOf(time));
            stmt.executeUpdate();

        }
    }
}
