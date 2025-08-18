
package itin_pc.model;

public class FormasPago {
    private int id;
    private String nombre;

    public FormasPago(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return nombre; 
    }
}

