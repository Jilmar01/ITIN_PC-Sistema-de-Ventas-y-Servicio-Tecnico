package itin_pc.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import itin_pc.model.ReporteServicioTecnico;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServicioTecnicoDAO {
    
    private final Connection conexion;

    public ServicioTecnicoDAO() {
        conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Obtine una lista de reportes de servicio técnico por marca.
     * 
     * @param marca Nombre de la marca a filtrar.
     * @return Lista de reportes de servicio técnico por marca.
     * @throws Excepciones si ocurre un error al obtener los reportes.
     */
    public List<ReporteServicioTecnico> reportePorMarca(String marca) throws Excepciones {
        
        List<ReporteServicioTecnico> reportes = new ArrayList<>();
        String sql = "SELECT st.servicio_tecnico_id, st.tipo_servicio, st.problemas_reportados, st.fecha_servicio, p.nombre AS nombre_producto, m.nombre AS marca_producto " +
                     "FROM servicio_tecnico st " +
                     "JOIN productos p ON st.producto_id = p.producto_id " +
                     "JOIN marcas m ON p.marca_id = m.marca_id " +
                     "WHERE m.nombre = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, marca);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ReporteServicioTecnico reporte = new ReporteServicioTecnico(
                        rs.getInt("servicio_tecnico_id"),
                        rs.getString("tipo_servicio"),
                        rs.getString("problemas_reportados"),
                        rs.getDate("fecha_servicio"),
                        rs.getString("nombre_producto"),
                        rs.getString("marca_producto")
                    );
                    reportes.add(reporte);
                }
            }
            
        } catch (SQLException e) {
            throw new Excepciones("ServicioTecnicoDAO", e.getMessage());
        }

        return reportes;
    }
    
}
