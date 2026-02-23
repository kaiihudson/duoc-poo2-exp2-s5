package cl.speedfast.model;

import cl.speedfast.gestor.ZonaDeCarga;

public class Repartidor implements Runnable {
    private int id;
    private final String nombre;
    private ZonaDeCarga zonaDeCarga = null;

    public Repartidor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Repartidor(String nombre) {
        this.nombre = nombre;
    }

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public void setZonaDeCarga(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Repartidor{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public void run() {
        if (zonaDeCarga == null) {
            return;
        }
        try {
            while (true) {
                try {
                    Pedido p = zonaDeCarga.retirarPedido();
                    if (p == null) {
                        break;
                    }
                    zonaDeCarga.logPedido(p, this);
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
