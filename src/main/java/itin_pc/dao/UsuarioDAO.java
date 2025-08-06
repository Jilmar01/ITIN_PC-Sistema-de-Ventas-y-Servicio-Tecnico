package itin_pc.dao;

import itin_pc.model.Usuario;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuarioDAO {

    private final Connection conexion;

    public UsuarioDAO() {
        this.conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Metodo para agregar un nuevo usuario a la base de datos.
     * 
     * @param usuario Objeto Usuario que contiene los datos del nuevo usuario.
     * @return El ID del usuario agregado, o -1 si no se pudo agregar.
     * @throws Excepciones si ocurre un error al agregar el usuario.
     */
    public int agregarUsuario(Usuario usuario) throws Excepciones {

        String sql = "INSERT INTO Usuarios (nombre_usuario, clave_acceso, rol, empleado_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getClaveAcceso());
            ps.setString(3, usuario.getRol());
            ps.setInt(4, usuario.getEmpleadoId());

            int filas = ps.executeUpdate();

            if (filas == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new Excepciones("UsuarioDAO", e.getMessage());
        }

        return -1;

    }

    /**
     * Obtiene un usuario por su ID.
     * 
     * @param usuario_id ID del usuario a buscar.
     * @return Usuario con los datos del usuario encontrado, o null si no se encuentra.
     * @throws Excepciones si ocurre un error al obtener el usuario.
     */
    public Usuario obtenerUsuarioPorId(int usuario_id) throws Excepciones {

        String sql = "SELECT * FROM Usuarios WHERE usuario_id = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, usuario_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario_id"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setClaveAcceso(rs.getString("clave_acceso"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setEmpleadoId(rs.getInt("empleado_id"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("UsuarioDAO", e.getMessage());
        }

        return usuario;
    }

    /**
     * Obtiene un usuario por su nombre de usuario y clave de acceso.
     * 
     * @param nombreUsuario Nombre de usuario a buscar.
     * @param claveAcceso Clave de acceso del usuario.
     * @return Usuario con los datos del usuario encontrado, o null si no se encuentra.
     * @throws Excepciones si ocurre un error al obtener el usuario.
     */
    public Usuario obtenerUsuario(String nombreUsuario, String claveAcceso) throws Excepciones {

        String sql = "SELECT * FROM Usuarios WHERE nombre_usuario = ? AND clave_acceso = ?";
        Usuario usuario = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, nombreUsuario);
            ps.setString(2, claveAcceso);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getInt("usuario_id"));
                    usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                    usuario.setClaveAcceso(rs.getString("clave_acceso"));
                    usuario.setRol(rs.getString("rol"));
                    usuario.setEmpleadoId(rs.getInt("empleado_id"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("UsuarioDAO", e.getMessage());
        }

        return usuario;
    }

}
