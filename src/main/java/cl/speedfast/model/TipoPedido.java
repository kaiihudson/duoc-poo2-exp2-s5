package cl.speedfast.model;

public enum TipoPedido {
    COMIDA,
    ENCOMIENDA,
    EXPRESS;

    public static TipoPedido find(String tipo){
        for(TipoPedido tipoPedido : TipoPedido.values()) {
            if (tipoPedido.name().equals(tipo)) {
                return tipoPedido;
            }
        }
        return null;
    }
}
