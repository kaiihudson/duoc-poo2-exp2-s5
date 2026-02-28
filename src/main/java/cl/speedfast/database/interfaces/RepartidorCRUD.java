package cl.speedfast.database.interfaces;

import cl.speedfast.model.Repartidor;

import java.util.List;

public interface RepartidorCRUD {
     Repartidor getRepartidor(int id);
     List<Repartidor> getRepartidores();
     void createRepartidor(Repartidor repartidor);
     void updateRepartidor(Repartidor repartidor);
     void deleteRepartidor(int id);
}
