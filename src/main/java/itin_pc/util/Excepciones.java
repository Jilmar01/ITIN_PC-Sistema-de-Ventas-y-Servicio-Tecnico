package itin_pc.util;

import java.sql.SQLException;

public class Excepciones extends Exception {
    
    // Constructor solo con mensaje
    public Excepciones(String mensaje) {
        super(mensaje);
    }
    
    // Constructor con mensaje y la clase de donde se genera la execpion
     public Excepciones(String clase, String mensaje) {
        super(clase + ": " + mensaje  );
    }
    
    public static void mensajeSQL(SQLException e, String DAO) {
        System.out.println(DAO + "DAO: " + e);
    }
        
}
