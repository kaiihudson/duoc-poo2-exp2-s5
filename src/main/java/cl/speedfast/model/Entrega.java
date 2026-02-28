package cl.speedfast.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;

public class Entrega {
    private int idEntrega;
    private int idPedido;
    private int idRepartidor;
    private Date fechaEntrega = new java.sql.Date(new java.util.Date().getTime());
    private Time horaEntrega = Time.valueOf(LocalTime.now());

    public Entrega() {

    }
    public Entrega(int idPedido, int idRepartidor) {
        this.idPedido = idPedido;
        this.idRepartidor = idRepartidor;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public Time getHoraEntrega() {
        return horaEntrega;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdEntrega(int idEntrega) {
        this.idEntrega = idEntrega;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public void setHoraEntrega(Time horaEntrega) {
        this.horaEntrega = horaEntrega;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "idEntrega=" + idEntrega +
                ", idPedido=" + idPedido +
                ", idRepartidor=" + idRepartidor +
                ", fechaEntrega=" + fechaEntrega +
                ", horaEntrega=" + horaEntrega +
                '}';
    }
}
