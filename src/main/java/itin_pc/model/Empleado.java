package itin_pc.model;

import java.util.Date;

public class Empleado {
    private int id;
    private String nombre;
    private String apellido;
    private String puesto;
    private Date fechaContratacion;
    private String estado; // "ACTIVO" o "INACTIVO"

    public Empleado() {}

    /**
     * Constructor para reportes de empleados.
     * 
     * @param id
     * @param nombre
     * @param apellido
     */
    public Empleado(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /**
     * Constructor para crear un nuevo empleado.
     * 
     * @param nombre
     * @param apellido
     * @param puesto
     * @param fechaContratacion
     */
    public Empleado(String nombre, String apellido, String puesto, Date fechaContratacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
    }

    public Empleado(int id, String nombre, String apellido, String puesto, Date fechaContratacion, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
    } 

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

