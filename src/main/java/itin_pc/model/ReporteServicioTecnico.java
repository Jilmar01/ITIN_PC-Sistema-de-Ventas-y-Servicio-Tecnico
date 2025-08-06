package itin_pc.model;

import java.sql.Date;

public class ReporteServicioTecnico extends ServicioTecnico {

    private String nombreProducto;
    private String marcaProducto;

    public ReporteServicioTecnico(int id, String tipoServicio, String problemasReportados, Date fechaServicio, String nombreProducto, String marcaProducot) {
        super(id, tipoServicio, problemasReportados, fechaServicio);
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducot;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getMarcaProducto() {
        return marcaProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

}
