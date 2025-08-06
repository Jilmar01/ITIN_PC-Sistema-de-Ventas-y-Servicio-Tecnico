    package itin_pc.dao;

import itin_pc.model.DetalleVenta;
import java.sql.Connection;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DetallesVentaDAO {
     
    private final Connection conexion;

    public DetallesVentaDAO() {
        conexion = ConexionBD.obtenerConexion();
    }


    /**
     * Inserta un nuevo detalle de venta en la base de datos.
     * 
     * @param detallesVenta Objeto DetalleVenta con los datos a insertar.
     * @return ID del detalle de venta insertado, o -1 si no se pudo insertar.
     * @throws Excepciones si ocurre un error al insertar el detalle de venta.
     */
    public int insertarDetallesVenta(DetalleVenta detallesVenta) throws Excepciones {
        
        String sql = "INSERT INTO detalles_venta (venta_id, producto_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, detallesVenta.getVentaId());
            ps.setInt(2, detallesVenta.getProductoId());
            ps.setInt(3, detallesVenta.getCantidad());
            ps.setDouble(4, detallesVenta.getPrecioUnitario());
            
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("DetallesVentaDAO", e.getMessage());
        }

        return -1;
    }

    
}
