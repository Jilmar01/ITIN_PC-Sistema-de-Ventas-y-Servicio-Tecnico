package itin_pc.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexionBD {

    private static Connection conexion = null;

    public static Connection obtenerConexion() {
        if (conexion != null) {
            return conexion;
        }

        try {
            Properties propiedades = new Properties();
            InputStream entrada = ConexionBD.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            if (entrada == null) {
                throw new RuntimeException("No se encontró el archivo de configuración.");
            }
            propiedades.load(entrada);

            // Obtener parámetros de conexión
            String controlador = propiedades.getProperty("db.driver");
            String url = propiedades.getProperty("db.url");
            String usuario = propiedades.getProperty("db.user");
            String contraseña = propiedades.getProperty("db.password");

            // Cargar controlador
            Class.forName(controlador);

            // Establecer conexión
            conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion exitosa a la base de datos");

        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos");
            e.printStackTrace();
        }

        return conexion;
    }
}
