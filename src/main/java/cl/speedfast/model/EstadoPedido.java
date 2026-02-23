package cl.speedfast.model;

public enum EstadoPedido {
    PENDIENTE,
    EN_REPARTO,
    ENTREGADO;

    public static EstadoPedido find(String estado){
        for(EstadoPedido estadoPedido : EstadoPedido.values()) {
            if (estadoPedido.name().equals(estado.toUpperCase())) {
                return estadoPedido;
            }
        }
        return null;
    }
}
