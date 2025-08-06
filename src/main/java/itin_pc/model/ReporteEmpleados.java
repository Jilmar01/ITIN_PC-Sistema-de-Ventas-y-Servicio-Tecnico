package itin_pc.model;

public class ReporteEmpleados extends Empleado {

    private int numeroVentas;

    public ReporteEmpleados(int id, String nombre, String apellido, int numeroVentas) {
        super(id, nombre, apellido);
        this.numeroVentas = numeroVentas;
    }

    public int getNumeroVentas() {
        return numeroVentas;
    }

    public void setNumeroVentas(int numeroVentas) {
        this.numeroVentas = numeroVentas;
    }
    
}
