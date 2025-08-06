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
     * Método para autenticar un usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param claveAcceso La contraseña del usuario.
     * @return Un objeto Usuario si la autenticación es exitosa; null en caso contrario.
     * @throws Excepciones 
     */
    public boolean loginItinPC(String nombreUsuario, String claveAcceso) throws Excepciones {
        if (!ValidacionDatos.esTextoGeneral(nombreUsuario) ||
                !ValidacionDatos.esTextoGeneral(claveAcceso)) {
            return false;
        }
        
        Usuario usuario = usuarioDAO.obtenerUsuario(nombreUsuario, claveAcceso);
        
        if(usuario != null) {
            System.out.println("Bienvenido " + usuario.getNombreUsuario());
            SesionUsuario.iniciarSesion(usuario);
            return true;
        } else {
            System.out.println("Usuario o contrasena incorrecta");
        }
        
        return false;
    }
    

    /**
     * Método para cerrar sesión del usuario actual.
     * 
     * @return true si la sesión se cierra correctamente; false en caso contrario.
     */
    public boolean logoutItinPC() {
        if (SesionUsuario.obtenerUsuarioActual() != null) {
            SesionUsuario.cerrarSesion();
            System.out.println("Vuelve pronto");
            return true;
        }
        return false;
    }
}