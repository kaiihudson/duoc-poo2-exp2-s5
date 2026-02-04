package cl.speedfast.model;

import cl.speedfast.gestor.ZonaDeCarga;

public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        try {
            while (!zonaDeCarga.getPedidosPendientes().isEmpty()){
                System.out.println( nombre + " intento tomar un nuevo pedido.");
                zonaDeCarga.retirarPedido();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Reparto interrumpido");
        }
    }
}
