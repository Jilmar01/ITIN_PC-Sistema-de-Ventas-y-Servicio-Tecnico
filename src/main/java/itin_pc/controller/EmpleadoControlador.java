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
     * Obtiene una lista de todos los empleados del sistema.
     * 
     * @return Lista de objetos Empleado con los datos de todos los empleados.
     * @throws Excepciones si ocurre un error al obtener los empleados o si el usuario no tiene permisos.
     */
    public List<Empleado> obtenerEmpleados() throws Excepciones {
        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if (!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }
        
        return empleadoDAO.obtenerEmpleados();
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
        
        int usuarioId = 0;
        
        try {
            usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        } catch (Exception e) {
            throw new Excepciones("No has iniciado Sesion");
        }
        
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
     * Obtiene un empleado por su ID.
     * 
     * @param empleadoId ID del empleado a obtener.
     * @return Objeto Empleado con los datos del empleado.
     * @throws Excepciones si ocurre un error al obtener el empleado o si el usuario no tiene permisos.
     */
    public Empleado obtenerEmpleado(int empleadoId) throws Excepciones {
        
        if (!ValidacionDatos.esEntero(empleadoId)) {
            throw new Excepciones("El ID del empleado es inválido.");
        }

        return empleadoDAO.obtenerEmpleadoPorId(empleadoId);
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


    /**
     * Busca empleados por un campo específico y su valor.
     * 
     * @param rol Rol por el que se busca a los empleados.
     * @return Lista de empleados que coinciden con el rol especificado.
     * @throws Excepciones si ocurre un error al buscar los empleados o si el usuario no tiene permisos.
     */
    public List<Empleado> obtenerEmpleadosPorRol(String rol) throws Excepciones {
        
        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if (!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }
        
        if (!ValidacionDatos.esTextoGeneral(rol)) {
            throw new Excepciones("El rol es inválido.");
        }
        
        return empleadoDAO.buscarEmpleados("u.rol", rol);
    }

    /**
     * Busca empleados por su nombre.
     * 
     * @param nombre Nombre del empleado a buscar.
     * @return Lista de empleados que coinciden con el nombre especificado.
     * @throws Excepciones si ocurre un error al buscar los empleados o si el usuario no tiene permisos.
     */
    public List<Empleado> buscarEmpleadoPorNombre(String nombre) throws Excepciones {
        
        int usuarioId = SesionUsuario.obtenerUsuarioActual().getId();
        
        if (!Autorizacion.esAdmin(usuarioId)) {
            throw new Excepciones("Usuario sin permiso de administrador");
        }
        
        if (!ValidacionDatos.esTextoGeneral(nombre)) {
            throw new Excepciones("El nombre es inválido.");
        }
        
        return empleadoDAO.buscarEmpleados("e.nombre", nombre);
    }
}
