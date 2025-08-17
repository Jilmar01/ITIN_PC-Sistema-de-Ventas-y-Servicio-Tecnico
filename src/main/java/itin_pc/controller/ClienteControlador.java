package itin_pc.controller;

import itin_pc.dao.ClienteDAO;
import itin_pc.model.Cliente;
import itin_pc.model.TipoCliente;
import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;
import java.util.List;

public class ClienteControlador {

    private final ClienteDAO clienteDAO;

    public ClienteControlador() {
        clienteDAO = new ClienteDAO();
    }

    /**
     * Metodo para obtener todos los clientes
     *
     * @return Lista de clientes disponibles
     * @throws Excepciones si ocurre un error al obtener los clientes
     */
    public List<Cliente> obtenerClientes() throws Excepciones {
        return clienteDAO.obtenerClientes();
    }

    /**
     * Metodo para agregar un nuevo cliente
     *
     * @param cliente Objeto Cliente a agregar
     * @return ID del cliente agregado
     * @throws Excepciones si los datos del cliente son inválidos o si ocurre un
     * error al agregar el cliente
     */
    public int agregarCliente(Cliente cliente) throws Excepciones {
        if (cliente == null) {
            throw new Excepciones("El cliente no puede ser nulo.");
        }

        if (!ValidacionDatos.esSoloTexto(cliente.getNombre())
                || !ValidacionDatos.esSoloTexto(cliente.getApellido())
                || !ValidacionDatos.esEntero(cliente.getTelefono())) {
            throw new Excepciones("Datos del cliente inválidos.");
        }

        return clienteDAO.insertarCliente(cliente);
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param clienteId ID del cliente a obtener.
     * @return Objeto Cliente con los datos del cliente.
     * @throws Excepciones si el ID es inválido o ocurre un error al obtener el
     * cliente.
     */
    public Cliente obtenerClientePorId(int clienteId) throws Excepciones {
        if (!ValidacionDatos.esEntero(clienteId)) {
            throw new Excepciones("ID de cliente inválido.");
        }
        return clienteDAO.obtenerClientePorId(clienteId);
    }

    /**
     * Actualiza los datos de un cliente en el sistema.
     *
     * @param cliente Objeto Cliente con los datos actualizados.
     * @return true si la actualización fue exitosa, false en caso contrario.
     * @throws Excepciones si los datos son inválidos o ocurre un error en la
     * actualización.
     */
    public boolean actualizarCliente(Cliente cliente) throws Excepciones {
        if (cliente == null || cliente.getId() <= 0) {
            throw new Excepciones("ID de cliente inválido.");
        }
        if (!ValidacionDatos.esSoloTexto(cliente.getNombre())
                || !ValidacionDatos.esSoloTexto(cliente.getApellido())
                || !ValidacionDatos.esEntero(cliente.getTelefono())) {
            throw new Excepciones("Datos del cliente inválidos.");
        }
        return clienteDAO.actualizarCliente(cliente);
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param clienteId ID del cliente a eliminar.
     * @return true si la eliminación fue exitosa, false en caso contrario.
     * @throws Excepciones si el ID es inválido o ocurre un error al eliminar el
     * cliente.
     */
    public boolean eliminarCliente(int clienteId) throws Excepciones {
        if (!ValidacionDatos.esEntero(clienteId)) {
            throw new Excepciones("ID de cliente inválido.");
        }
        return clienteDAO.eliminarCliente(clienteId);
    }

    /**
     * Metodo para obtener clientes VIP por mes y año
     *
     * @param mes Mes del año a filtrar
     * @param anio Año a filtrar
     * @return Lista de clientes VIP
     * @throws Excepciones si los datos del mes o año son inválidos
     */
    public List<Cliente> obtenerClientesPorTipo(String tipoCliente, int mes, int anio) throws Excepciones {

        if (!ValidacionDatos.esMesValido(mes)
                || !ValidacionDatos.esAnioValido(anio)) {
            throw new Excepciones("Datos de mes o año inválidos.");
        }

        return clienteDAO.obtenerClientesPorTipoYFecha(tipoCliente, mes, anio);

    }

    public List<TipoCliente> obtenerTiposCliente() throws Excepciones {
        return clienteDAO.obtenerTiposCliente();
    }

    public TipoCliente obtenerTipoClientePorId(int tipoClienteId) throws Excepciones {

        if (!ValidacionDatos.esEntero(tipoClienteId)) {
            throw new Excepciones("ID de tipoCliente inválido");
        }
        return clienteDAO.obTipoClientePorId(tipoClienteId);
    }

}
