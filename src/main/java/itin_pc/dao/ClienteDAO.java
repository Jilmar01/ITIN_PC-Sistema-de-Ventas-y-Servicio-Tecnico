package itin_pc.dao;

import itin_pc.model.Cliente;
import itin_pc.model.TipoCliente;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    private final Connection conexion;

    public ClienteDAO() {
        conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Obtiene una lista de todos los clientes.
     * 
     * @return Lista de clientes
     * @throws Excepciones si ocurre un error al obtener los clientes.
     */
    public List<Cliente> obtenerClientes() throws Excepciones {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setId(rs.getInt("cliente_id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setCorreo(rs.getString("correo"));
                c.setTelefono(rs.getString("telefono"));
                c.setTipoClienteId(rs.getInt("tipo_cliente_id"));
                clientes.add(c);
            }

        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return clientes;
    }

    /**
     * Obtiene un cliente por su ID.
     * 
     * @param id ID del cliente a buscar.
     * @return Cliente encontrado o null si no existe.
     * @throws Excepciones si ocurre un error al obtener el cliente.
     */
    public Cliente obtenerClientePorId(int id) throws Excepciones {

        String sql = "SELECT * FROM clientes WHERE cliente_id = ?";
        Cliente cliente = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("cliente_id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setTipoClienteId(rs.getInt("tipo_cliente_id"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return cliente;
    }

    /**
     * Obtiene clientes por un atributo específico y su valor.
     *
     * @param nombreAtributo Nombre del atributo (por ejemplo, "correo",
     *                       "telefono").
     * @param valorAtributo  Valor del atributo a buscar.
     * @return Cliente encontrado o null si no existe.
     * @throws Excepciones si ocurre un error al obtener el cliente.
     */
    public List<Cliente> obtenerClientePorAtributo(String nombreAtributo, String valorAtributo) throws Excepciones {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes WHERE " + nombreAtributo + " LIKE ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, "%" + valorAtributo + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("cliente_id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setTipoClienteId(rs.getInt("tipo_cliente_id"));
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return clientes;
    }

    /**
     * Inserta un nuevo cliente en la base de datos.
     * 
     * @param cliente Objeto Cliente con los datos a insertar.
     * @return ID del cliente insertado, o -1 si no se pudo insertar.
     * @throws Excepciones si ocurre un error al insertar el cliente.
     */
    public int insertarCliente(Cliente cliente) throws Excepciones {

        String sql = "INSERT INTO clientes (nombre, apellido, correo, telefono, tipo_cliente_id) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getTipoClienteId());

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
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return -1;
    }

    /**
     * Actualiza los datos de un cliente.
     * 
     * @param cliente Objeto Cliente con los nuevos datos.
     * @return true si se actualiza correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al actualizar el cliente.
     */
    public boolean actualizarCliente(Cliente cliente) throws Excepciones {

        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, tipo_cliente_id = ? WHERE id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getCorreo());
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5, cliente.getTipoClienteId());
            ps.setInt(6, cliente.getId());

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }
    }

    /**
     * Elimina un cliente por su ID.
     * 
     * @param id ID del cliente a eliminar.
     * @return true si se elimina correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al eliminar el cliente.
     */
    public boolean eliminarCliente(int id) throws Excepciones {
        String sql = "DELETE FROM clientes WHERE cliente_id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);

            int filas = ps.executeUpdate();
            return filas > 0;

        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }
    }

    /**
     * Obtiene una lista de clientes por tipo y fecha.
     * 
     * @param tipoCliente Tipo de cliente (por ejemplo, "VIP", "NORMAL").
     * @param mes         el mes para filtrar los clientes (1-12).
     * @param anio        el año para filtrar los clientes.
     * @return Lista de clientes que coinciden con el tipo y fecha especificados.
     * @throws Excepciones si los datos de tipo cliente, mes o año son inválidos.
     */
    public List<Cliente> obtenerClientesPorTipoYFecha(String tipoCliente, int mes, int anio) throws Excepciones {

        String sql = """
                    SELECT DISTINCT c.cliente_id, c.nombre, c.apellido, c.correo, c.telefono, c.tipo_cliente_id
                    FROM clientes c
                    INNER JOIN tipos_cliente tc ON tc.tipo_cliente_id = c.tipo_cliente_id
                    INNER JOIN ventas v ON c.cliente_id = v.cliente_id
                    WHERE tc.tipo = ? AND MONTH(v.fecha) = ? AND YEAR(v.fecha) = ?
                """;

        List<Cliente> clientes = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, tipoCliente);
            ps.setInt(2, mes);
            ps.setInt(3, anio);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cliente c = new Cliente();
                    c.setId(rs.getInt("cliente_id"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setCorreo(rs.getString("correo"));
                    c.setTelefono(rs.getString("telefono"));
                    c.setTipoClienteId(rs.getInt("tipo_cliente_id"));
                    clientes.add(c);
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return clientes;
    }

    /**
     * Obtiene una lista de todos los tipos de cliente.
     * 
     * @return Lista de tipos de cliente.
     * @throws Excepciones si ocurre un error al obtener los tipos de cliente.
     */
    public List<TipoCliente> obtenerTiposCliente() throws Excepciones {
        List<TipoCliente> tipos = new ArrayList<>();
        String sql = "SELECT * FROM tipos_cliente";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                TipoCliente tipo = new TipoCliente();
                tipo.setId(rs.getInt("tipo_cliente_id"));
                tipo.setTipo(rs.getString("tipo"));
                tipos.add(tipo);
            }

        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return tipos;
    }

    public TipoCliente obTipoClientePorId(int id) throws Excepciones {
        String sql = "SELECT * FROM tipos_cliente WHERE tipo_cliente_id = ?";
        TipoCliente tipoCliente = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tipoCliente = new TipoCliente();
                    tipoCliente.setId(rs.getInt("tipo_cliente_id"));
                    tipoCliente.setTipo(rs.getString("tipo"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ClienteDAO", e.getMessage());
        }

        return tipoCliente;
    }

}
