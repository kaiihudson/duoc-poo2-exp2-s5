package cl.speedfast.app;

import cl.speedfast.gestor.ZonaDeCarga;
import cl.speedfast.model.Pedido;
import cl.speedfast.model.Repartidor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args){
        // crea zona de carga compartida
        ZonaDeCarga zc = new ZonaDeCarga();
        // agrega al menos 5 pedidos
        zc.agregarPedido(new Pedido("direcicon 1"));
        zc.agregarPedido(new Pedido("direccion 2"));
        zc.agregarPedido(new Pedido("direccion 3"));
        zc.agregarPedido(new Pedido("direccion 4"));
        zc.agregarPedido(new Pedido("direccion 5"));
        // crea 3 repartidores que son hilos
        Repartidor r1 = new Repartidor("Sebastian", zc);
        Repartidor r2 = new Repartidor("ebastianS", zc);
        Repartidor r3 = new Repartidor("bastianSe", zc);
        // usa executor service
        ExecutorService es = Executors.newFixedThreadPool(3);
        // ejecuta los repartidores como hilos aparentes
        es.execute(r1);
        es.execute(r2);
        es.execute(r3);
        // cierra la conexion una vez esta terminado el proceso.
        es.shutdown();
        try {
            if (es.awaitTermination(30, TimeUnit.SECONDS)) {
                System.out.println("Killing");
                es.shutdownNow();
            }
        } catch (InterruptedException e ){
            es.shutdownNow();
        }
    }
}
