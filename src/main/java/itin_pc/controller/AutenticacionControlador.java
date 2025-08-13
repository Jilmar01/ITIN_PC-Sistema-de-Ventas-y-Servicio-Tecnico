package itin_pc.controller;

import itin_pc.dao.UsuarioDAO;
import itin_pc.model.Usuario;
import itin_pc.util.Excepciones;
import itin_pc.util.SesionUsuario;
import itin_pc.util.ValidacionDatos;

public class AutenticacionControlador {

    private final UsuarioDAO usuarioDAO;

    public AutenticacionControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Método para autenticar un usuario en el sistema.
     *
     * Valida que el nombre de usuario y la clave tengan formato correcto, luego
     * consulta la base de datos para verificar las credenciales. Si la
     * autenticación es exitosa, inicia sesión guardando el usuario activo en la
     * sesión global.
     *
     * @param nombreUsuario El nombre de usuario a autenticar.
     * @param claveAcceso La contraseña asociada al usuario.
     * @return true si la autenticación fue exitosa y se inició sesión, false en
     * caso contrario.
     * @throws Excepciones Si ocurre un error en la capa de acceso a datos o
     * validación.
     */
    public boolean loginItinPC(String nombreUsuario, String claveAcceso) throws Excepciones {
       
        if (!ValidacionDatos.esTextoGeneral(nombreUsuario)
                || !ValidacionDatos.esTextoGeneral(claveAcceso)) {
            return false;
        }

        // Buscar usuario en la base de datos con las credenciales proporcionadas
        Usuario usuario = usuarioDAO.obtenerUsuario(nombreUsuario, claveAcceso);

        if (usuario != null) {
            // Guardar el usuario autenticado en la sesión global para uso del sistema
            SesionUsuario.iniciarSesion(usuario);
            return true;
        }

        return false;
    }
}
