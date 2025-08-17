package itin_pc.controller;

import itin_pc.dao.UsuarioDAO;
import itin_pc.model.Usuario;
import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;

public class UsuarioControlador {

    UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Método para agregar un nuevo usuario al sistema.
     * 
     * @param usuario Objeto Usuario que contiene los datos del nuevo usuario
     * @return ID del usuario agregado
     * @throws Excepciones si hay un error al agregar el usuario o si los datos son
     *                     inválidos
     */
    public int agregarUsuario(Usuario usuario) throws Excepciones {
        if (usuario.getEmpleadoId() <= 0) {
            throw new Excepciones("No existe este usuario");
        }
        if (!ValidacionDatos.esTextoGeneral(usuario.getNombreUsuario())
                || !ValidacionDatos.esTextoGeneral(usuario.getClaveAcceso())
                || !ValidacionDatos.esSoloTexto(usuario.getRol())) {
            throw new Excepciones("Los datos del usuario estan incompletos o son inválidos.");
        }

        int usuario_id = usuarioDAO.agregarUsuario(usuario);

        return usuario_id;
    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param usuarioId ID del usuario a obtener.
     * @return Objeto Usuario con los datos del usuario.
     * @throws Excepciones si ocurre un error al obtener el usuario.
     */
    public Usuario obtenerUsuario(int usuarioId) throws Excepciones {
        if (usuarioId <= 0) {
            throw new Excepciones("ID de usuario inválido");
        }

        Usuario usuario = usuarioDAO.obtenerUsuarioPorId(usuarioId);

        if (usuario == null) {
            throw new Excepciones("Usuario no encontrado");
        }

        return usuario;
    }

    public Usuario obtenerUsuarioPorEmpleadoId(int empleadoId) throws Excepciones {
        if (empleadoId <= 0) {
            throw new Excepciones("ID de usuario inválido");
        }

        Usuario usuario = usuarioDAO.obtenerUsuarioPorEmpleadoId(empleadoId);

        if (usuario == null) {
            throw new Excepciones("Usuario no encontrado");
        }

        return usuario;
    }

    /**
     * Genera un nombre de usuario basado en el nombre, apellido y rol del empleado.
     * 
     * @param nombre   nombre del empleado
     * @param apellido apellido del empleado
     * @param rol      rol del empleado como "ADMIN", "VENDEDOR", "TECNICO".
     * @return Usuario objeto con el nombre de usuario, contraseña generados y rol
     *         asignado.
     */
    public Usuario generarNombreUsuario(String nombre, String apellido, String rol) {

        if (!ValidacionDatos.esSoloTexto(nombre) || !ValidacionDatos.esSoloTexto(apellido)
                || !ValidacionDatos.esSoloTexto(rol)) {
            return null;
        }

        Usuario usuario = new Usuario();

        // Suponiendo que Empleado tiene los métodos getNombre() y getApellido()
        String nombreLimpio = nombre.toLowerCase().replaceAll("\\s+", "");
        String apellidoLimpio = apellido.toLowerCase().replaceAll("\\s+", "");

        String nombreCorto = nombreLimpio.length() >= 2 ? nombreLimpio.substring(0, 2) : nombre;
        String apellidoCorto = apellidoLimpio.length() >= 2 ? apellidoLimpio.substring(0, 2) : apellido;

        String usuarioStr = nombreCorto + apellidoLimpio;

        String usuarioFinal = usuarioStr + "@itinpc.com";
        String contraseña = apellidoCorto + rol.toLowerCase() + "123";

        usuario.setNombreUsuario(usuarioFinal);
        usuario.setClaveAcceso(contraseña);
        usuario.setRol(rol.toUpperCase());

        return usuario;
    }

    /**
     * Actualiza los datos de un usuario en el sistema.
     * 
     * @param usuario Objeto Usuario con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws Excepciones si los datos son inválidos o ocurre un error en la
     *                     actualización.
     */
    public boolean actualizarUsuario(Usuario usuario) throws Excepciones {
        if (usuario == null || usuario.getId() <= 0) {
            throw new Excepciones("ID de usuario inválido");
        }
        if (!ValidacionDatos.esTextoGeneral(usuario.getNombreUsuario())
                || !ValidacionDatos.esTextoGeneral(usuario.getClaveAcceso())
                || !ValidacionDatos.esSoloTexto(usuario.getRol())) {
            throw new Excepciones("Los datos del usuario están incompletos o son inválidos.");
        }
        return usuarioDAO.actualizarUsuario(usuario);
    }

}
