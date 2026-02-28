package cl.speedfast.database.interfaces;

import cl.speedfast.model.Entrega;

import java.util.List;

public interface EntregaCRUD {
     void crearEntrega(Entrega entrega);
     Entrega getById(int idEntrega);
     List<Entrega> getAllEntregas();
    void eliminarEntrega(int id);
     void actualizarEntrega(Entrega entrega);
}
