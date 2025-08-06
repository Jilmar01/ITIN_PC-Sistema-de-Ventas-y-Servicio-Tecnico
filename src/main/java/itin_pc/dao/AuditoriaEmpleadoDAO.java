package itin_pc.dao;

import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AuditoriaEmpleadoDAO {
    
    private final Connection conexion;

    public AuditoriaEmpleadoDAO() {
        this.conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Metodo para registrar la baja de un empleado en la auditoria.
     * 
     * @param empleado_id ID del empleado que se da de baja
     * @param usuario_id ID del usuario que realiza la accion
     * @param accionAuditoria Descripcion de la accion de auditoria
     * @return true si se registro correctamente, false en caso contrario
     * @throws Excepciones 
     */
    public boolean registrarBajaEmpleado(int empleado_id, int usuario_id, String accionAuditoria) throws Excepciones {
        
        String sql = "{CALL DarDeBajaEmpleado(?, ?, ?)}";

        try (CallableStatement cs = conexion.prepareCall(sql)) {
            cs.setInt(1, empleado_id);
            cs.setInt(2, usuario_id);
            cs.setString(3, accionAuditoria);

            cs.execute();
            return true;
        } catch (SQLException e) {
            throw new Excepciones("AuditoriaEmpleadoDAO", e.getMessage());
        }
        
    }
}
