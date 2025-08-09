package itin_pc.controller;

import itin_pc.dao.UsuarioDAO;
import itin_pc.model.Usuario;
import itin_pc.seguridad.Autorizacion;
import itin_pc.util.Excepciones;
import itin_pc.util.SesionUsuario;
import itin_pc.util.ValidacionDatos;

public class AutenticacionControlador {

    private final UsuarioDAO usuarioDAO;

    public AutenticacionControlador() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    /**
     * Enum para representar los posibles resultados del login.
     */
    public enum ResultadoLogin {
        ADMIN, VENDEDOR, TECNICO, ERROR
    }

    /**
     * Método para autenticar un usuario.
     * 
     * @param nombreUsuario El nombre de usuario.
     * @param claveAcceso La contraseña del usuario.
     * @return El resultado del login (ADMIN, VENDEDOR, TECNICO, ERROR).
     * @throws Excepciones 
     */
    public ResultadoLogin loginItinPC(String nombreUsuario, String claveAcceso) throws Excepciones {
        if (!ValidacionDatos.esTextoGeneral(nombreUsuario) ||
                !ValidacionDatos.esTextoGeneral(claveAcceso)) {
            return ResultadoLogin.ERROR;
        }
        
        Usuario usuario = usuarioDAO.obtenerUsuario(nombreUsuario, claveAcceso);
        
        if(usuario != null) {
            
            SesionUsuario.iniciarSesion(usuario);
            
            if (Autorizacion.esAdmin(usuario.getId())) return ResultadoLogin.ADMIN;
            if (Autorizacion.esVendedor(usuario.getId())) return ResultadoLogin.VENDEDOR;
            if (Autorizacion.esTecnico(usuario.getId())) return ResultadoLogin.TECNICO;   
        }
        
        return ResultadoLogin.ERROR;
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