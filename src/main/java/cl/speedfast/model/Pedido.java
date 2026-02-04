package cl.speedfast.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;

    public Pedido(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public int getId() {
        return id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Pedido{");
        sb.append("id=").append(id);
        sb.append(", direccionEntrega='").append(direccionEntrega).append('\'');
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }
}
