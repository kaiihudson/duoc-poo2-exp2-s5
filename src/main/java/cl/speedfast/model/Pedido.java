package cl.speedfast.model;

import java.util.UUID;

public class Pedido {
    private final UUID id;
    private String direccionEntrega;
    private EstadoPedido estado;
    private TipoPedido tipo;

    public Pedido() {
        this.id = UUID.randomUUID();
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(String direccionEntrega) {
        this.id = UUID.randomUUID();
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
    }

    public UUID getId() {
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
        return "Pedido{" + "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", estado=" + estado +
                '}';
    }
}
