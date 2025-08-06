/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itin_pc.dao;

import itin_pc.model.Empleado;
import itin_pc.model.ReporteEmpleados;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USER
 */
public class EmpleadoDAO {

    private final Connection conexion;

    public EmpleadoDAO() {
        conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Obtiene una lista de todos los empleados.
     * 
     * @return Lista de empleados
     * @throws Excepciones 
     */
    public List<Empleado> obtenerEmpleados() throws Excepciones {

        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Statement stmt = conexion.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("empleado_id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setPuesto(rs.getString("puesto"));
                e.setFechaContratacion(rs.getDate("fecha_contratacion"));
                e.setEstado(rs.getString("estado"));
                empleados.add(e);
            }

        } catch (SQLException e) {
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }

        return empleados;
    }

    /**
     * Inserta un nuevo empleado en la base de datos.
     * 
     * @param empleado Objeto Empleado con los datos del nuevo empleado.
     * @return ID del nuevo empleado si se agrega correctamente,
     *         {@code -1} si hay errores de autenticacion o validación.
     * @throws Excepciones si ocurre un error al insertar el empleado.
     */
    public int insertarEmpleado(Empleado empleado) throws Excepciones {
        
        String sql = "INSERT INTO empleados (nombre, apellido, puesto, fecha_contratacion) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getPuesto());
            ps.setDate(4, new java.sql.Date(empleado.getFechaContratacion().getTime()));

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
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }
        
        return -1;
    }

    /**
     * Obtiene un empleado por su ID.
     * 
     * @param empleado_id ID del empleado a buscar.
     * @return Empleado con los datos del empleado encontrado,
     * @throws Excepciones si ocurre un error al obtener el empleado.
     */
    public Empleado obtenerEmpleadoPorId(int empleado_id) throws Excepciones {
        Empleado empleado = null;
        
        String sql = "SELECT * FROM empleados WHERE empleado_id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, empleado_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setId(rs.getInt("empleado_id"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                    empleado.setPuesto(rs.getString("puesto"));
                    empleado.setFechaContratacion(rs.getDate("fecha_contratacion"));
                    empleado.setEstado(rs.getString("estado"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }
        
        return empleado;
    }

    /**
     * Actualiza los datos de un empleado.
     * 
     * @param empleado Objeto Empleado con los nuevos datos.
     * @return true si se actualiza correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al actualizar el empleado.
     */
    public boolean actualizarEmpleado(Empleado empleado) throws Excepciones {

        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, puesto = ?, fecha_contratacion = ?, estado = ? WHERE empleado_id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellido());
            ps.setString(3, empleado.getPuesto());
            ps.setDate(4, new java.sql.Date(empleado.getFechaContratacion().getTime()));
            ps.setString(5, empleado.getEstado());
            ps.setInt(6, empleado.getId());

            int filas = ps.executeUpdate();

            return filas > 0;
        } catch (SQLException e) {
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }
    }

    /**
     * Elimina un empleado por su ID.
     * 
     * @param empleado_id ID del empleado a eliminar.
     * @return true si se elimina correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al eliminar el empleado.
     */
    public boolean eliminarEmpleado(int empleado_id) throws Excepciones {
        
        String sql = "DELETE FROM empleados WHERE empleado_id = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, empleado_id);
            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }
    }

    /**
     * Obtiene un reporte de empleados con el número de ventas realizadas por cada uno.
     * 
     * @return Lista de ReporteEmpleados con el número de ventas por empleado.
     * @throws Excepciones si ocurre un error al obtener el reporte.
     */
    public List<ReporteEmpleados> obtenerNumeroDeVentasDeEmpleados() throws Excepciones {
        
        List<ReporteEmpleados> reporte = new ArrayList<>();
        String sql = """
                    SELECT e.empleado_id, e.nombre, e.apellido, COUNT(v.venta_id) AS numero_ventas
                    FROM empleados e
                    INNER JOIN ventas v ON e.empleado_id = v.empleado_id
                    GROUP BY e.empleado_id, e.nombre, e.apellido
                    ORDER BY numero_ventas DESC
                """;

        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ReporteEmpleados re = new ReporteEmpleados(
                        rs.getInt("empleado_id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getInt("numero_ventas")
                );
                reporte.add(re);
            }
        } catch (SQLException e) {
            throw new Excepciones("EmpleadoDAO", e.getMessage());
        }

        return reporte;
    }
    

}
