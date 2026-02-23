package cl.speedfast.model;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado;
    private TipoPedido tipo;

    public Pedido() {
        this.estado = EstadoPedido.PENDIENTE;
        this.tipo = TipoPedido.COMIDA;
    }

    public Pedido(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
        this.estado = EstadoPedido.PENDIENTE;
    }

    public Pedido(int id, String direccionEntrega, EstadoPedido estado, TipoPedido tipo) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado;
        this.tipo = tipo;
    }

    public TipoPedido getTipo() {
        return tipo;
    }

    public void setTipo(TipoPedido tipo) {
        this.tipo = tipo;
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
        return "Pedido{" + "id=" + id +
                ", direccionEntrega='" + direccionEntrega + '\'' +
                ", estado=" + estado +
                '}';
    }
}
