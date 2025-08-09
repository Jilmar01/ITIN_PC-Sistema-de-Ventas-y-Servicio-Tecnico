package itin_pc.controller;

import java.util.List;

import itin_pc.dao.AuditoriaEmpleadoDAO;
import itin_pc.dao.EmpleadoDAO;
import itin_pc.model.Empleado;
import itin_pc.model.ReporteEmpleados;
import itin_pc.seguridad.Autorizacion;
import itin_pc.util.Excepciones;
import itin_pc.util.SesionUsuario;
import itin_pc.util.ValidacionDatos;

public class EmpleadoControlador {

    private final EmpleadoDAO empleadoDAO;
    private final AuditoriaEmpleadoDAO auditoriaEmpleadoDAO;

    public EmpleadoControlador() {
        empleadoDAO = new EmpleadoDAO();
        auditoriaEmpleadoDAO = new AuditoriaEmpleadoDAO();
    }

    /**
     * Agrega un nuevo empleado al sistema
     * 
     * Ejemplo de uso:
     * 
     * <pre>
     * agregarEmpleado(new Empleado("Juan", "Pérez", "Gerente", "2023-10-01"));
     * </pre>
     * 
     * @param empleado Objeto Empleado con los datos del nuevo empleado.
     * @return ID del nuevo empleado si se agrega correctamente,
     * {@code -1} si hay errores de autenticacion o validación.
     * @throws itin_pc.util.Excepciones
     */
    public int agregarEmpleado(Empleado empleado) throws Excepciones {
        
        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if (!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }
        
        if (!ValidacionDatos.esSoloTexto(empleado.getNombre())
                || !ValidacionDatos.esSoloTexto(empleado.getApellido())
                || !ValidacionDatos.esSoloTexto(empleado.getPuesto())) {
            throw new Excepciones("Los datos del empleado estan incompletos o son inválidos.");
        }

        int empleadoId = empleadoDAO.insertarEmpleado(empleado);

        return empleadoId;
    }

    /**
     * Elimina (da de baja) un empleado y registra la auditoría de la acción
     * realizada.Ejemplo de uso:
     *<pre>
     * eliminarEmpleado(25, 1, "Despedido");
     * </pre>
     *
     * @param empleado_id ID del empleado a dar de baja.
     * @param accion Descripción de la acción realizada (por ejemplo,
     * "Despedido", "Renuncia").
     * 
     * @return {@code true} si la baja y el registro de auditoría se realizaron
     * correctamente.
     * {@code false} si no se cumplen las validaciones o el
     * usuario no tiene permisos.
     * @throws itin_pc.util.Excepciones
     */
    public boolean eliminarEmpleado(int empleado_id, String accion) throws Excepciones {

        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if (!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }

        if (!ValidacionDatos.esEntero(empleado_id) ||
                !ValidacionDatos.esTextoGeneral(accion)) {
            throw new Excepciones("Los datos del empleado estan incompletos o son inválidos.");
        }

        return auditoriaEmpleadoDAO.registrarBajaEmpleado(empleado_id, usuarioId, accion);
    }

    /**
     * Obtine una lista de todos los empleados con sus ventas.
     * 
     * @return Lista de empleados con sus datos y ventas.
     * @throws Excepciones si ocurre un error al obtener los empleados.
     */
    public List<ReporteEmpleados> empleadosConMasVentas() throws Excepciones {
        
        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if(!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }
        
        return empleadoDAO.obtenerNumeroDeVentasDeEmpleados();
    }
}
