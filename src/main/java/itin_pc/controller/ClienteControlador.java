package itin_pc.controller;

import itin_pc.dao.ClienteDAO;
import itin_pc.model.Cliente;
import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;
import java.util.List;

public class ClienteControlador {

    private ClienteDAO clienteDAO;

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
     * Metodo para obtener clientes VIP por mes y año
     * 
     * @param mes Mes del año a filtrar
     * @param anio Año a filtrar
     * @return Lista de clientes VIP
     * @throws Excepciones si los datos del mes o año son inválidos
     */
    public List<Cliente> obtenerClientesVIP(int mes, int anio) throws Excepciones {
        
        if (!ValidacionDatos.esMesValido(mes) ||
                !ValidacionDatos.esAnioValido(anio)) {
            throw new Excepciones("Datos de mes o año inválidos.");
        }

        return clienteDAO.obtenerClientesPorTipoYFecha("VIP", mes, anio);

    }

}
