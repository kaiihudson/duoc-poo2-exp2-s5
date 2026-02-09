package cl.speedfast.model;

import cl.speedfast.gestor.ZonaDeCarga;

public class Repartidor implements Runnable {
    private final String nombre;
    private final ZonaDeCarga zonaDeCarga;

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    @Override
    public void run() {
        try {
            while (true) {
                try {
                    Pedido p = zonaDeCarga.retirarPedido();
                    if (p == null) {
                        break;
                    }
                    int delay = zonaDeCarga.repartirPedido(p);
                    System.out.println("Entregando pedido " + p.getId() +
                            " a cargo de " + nombre +
                            " tu pedido tiene una demora de: " + delay/1000 +
                            " segundos");
                    Thread.sleep(delay);
                    zonaDeCarga.entregarPedido(p);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("Reparto interrumpido");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
