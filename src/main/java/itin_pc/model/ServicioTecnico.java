package itin_pc.model;

import java.util.Date;

public class ServicioTecnico {
    
    private int id;
    private int productoId;
    private int clienteId;
    private String tipoServicio;
    private String problemasReportados;
    private Date fechaServicio;
    private int empleadoId;
    private int marcaId;

    public ServicioTecnico() {
    }
    
    public ServicioTecnico(int id, String tipoServicio, String problemasReportados, Date fechaServicio) {
        this.id = id;
        this.tipoServicio = tipoServicio;
        this.problemasReportados = problemasReportados;
        this.fechaServicio = fechaServicio;
    }

    public ServicioTecnico(int id, int productoId, int clienteId, String tipoServicio, String problemasReportados, Date fechaServicio, int empleadoId, int marcaId) {
        this.id = id;
        this.productoId = productoId;
        this.clienteId = clienteId;
        this.tipoServicio = tipoServicio;
        this.problemasReportados = problemasReportados;
        this.fechaServicio = fechaServicio;
        this.empleadoId = empleadoId;
        this.marcaId = marcaId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getProblemasReportados() {
        return problemasReportados;
    }

    public void setProblemasReportados(String problemasReportados) {
        this.problemasReportados = problemasReportados;
    }

    public Date getFechaServicio() {
        return fechaServicio;
    }

    public void setFechaServicio(Date fechaServicio) {
        this.fechaServicio = fechaServicio;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getMarcaId() {
        return marcaId;
    }

    public void setMarcaId(int marcaId) {
        this.marcaId = marcaId;
    }
    
    
}
