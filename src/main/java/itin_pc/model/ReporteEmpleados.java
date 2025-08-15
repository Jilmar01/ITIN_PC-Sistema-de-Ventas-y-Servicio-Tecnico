package itin_pc.model;

public class ReporteEmpleados extends Empleado {

    private int numeroVentas;
    private double montoTotal;

    public ReporteEmpleados(int id, String nombre, String apellido, int numeroVentas, double montoTotal) {
        super(id, nombre, apellido);
        this.numeroVentas = numeroVentas;
        this.montoTotal = montoTotal;
    }

    public int getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(int numeroVentas) {
        this.numeroVentas = numeroVentas;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }
    
}
