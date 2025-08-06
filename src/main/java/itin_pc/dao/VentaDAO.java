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
}
