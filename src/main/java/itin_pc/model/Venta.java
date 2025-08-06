package itin_pc.model;

import java.util.Date;

public class Venta {
    private int id;
    private int empleadoId;
    private int clienteId;
    private Date fecha;
    private double total;
    private int formaPagoId;

    public Venta() {}

    public Venta(int id, int empleadoId, int clienteId, Date fecha, double total, int formaPagoId) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
        this.formaPagoId = formaPagoId;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getFormaPagoId() {
        return formaPagoId;
    }

    public void setFormaPagoId(int formaPagoId) {
        this.formaPagoId = formaPagoId;
    }

    
    
}
