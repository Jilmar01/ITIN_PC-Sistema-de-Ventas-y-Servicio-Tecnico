package itin_pc.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;

import itin_pc.model.Venta;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VentaDAO {
    
    private final Connection conexion;

    public VentaDAO() {
        conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Inserta una nueva venta en la base de datos.
     *
     * @param venta Objeto Venta que contiene los datos de la venta a insertar.
     * @return El ID de la venta insertada, o -1 si no se pudo insertar.
     * @throws Excepciones si ocurre un error al insertar la venta.
     */
    public int insertarVenta(Venta venta) throws Excepciones {
        
        String sql = "INSERT INTO ventas (empleado_id, cliente_id, forma_pago_id) VALUES (?, ?, ?)";
        
        int ventaId = -1;

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, venta.getEmpleadoId());
            ps.setInt(2, venta.getClienteId());
            ps.setInt(3, venta.getFormaPagoId());
        
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("VentaDAO", e.getMessage());
        }

        return ventaId;
    }

    /**
     * Obtiene una venta por su ID.
     *
     * @param id El ID de la venta a obtener.
     * @return Un objeto Venta con los datos de la venta, o null si no se encuentra.
     * @throws Excepciones si ocurre un error al obtener la venta.
     */
    public Venta obtenerVentaPorId(int id) throws Excepciones {
        String sql = "SELECT * FROM ventas WHERE venta_id = ?";
        Venta venta = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta();
                    venta.setId(rs.getInt("venta_id"));
                    venta.setEmpleadoId(rs.getInt("empleado_id"));
                    venta.setClienteId(rs.getInt("cliente_id"));
                    venta.setFecha(rs.getDate("fecha"));
                    venta.setFormaPagoId(rs.getInt("forma_pago_id"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("VentaDAO", e.getMessage());
        }

        return venta;
    }
}
