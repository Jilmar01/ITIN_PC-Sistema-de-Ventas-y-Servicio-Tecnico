package itin_pc.model;

public class Usuario {
    private int id;
    private String nombreUsuario;
    private String claveAcceso;
    private String rol;
    private int empleadoId;

    public Usuario() {}

    public Usuario(String nombreUsuario, String claveAcceso, String rol, int empleadoId) {
        this.nombreUsuario = nombreUsuario;
        this.claveAcceso = claveAcceso;
        this.rol = rol;
        this.empleadoId = empleadoId;
    }

    public Usuario(int id, String nombreUsuario, String claveAcceso, String rol, int empleadoId) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.claveAcceso = claveAcceso;
        this.rol = rol;
        this.empleadoId = empleadoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    
}

