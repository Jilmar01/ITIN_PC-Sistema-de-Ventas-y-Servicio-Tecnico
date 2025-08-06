package itin_pc.controller;

import itin_pc.dao.ServicioTecnicoDAO;
import itin_pc.model.ReporteServicioTecnico;
import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;
import java.util.List;

public class ServicioTecnicoControlador {

    private ServicioTecnicoDAO servicioTecnicoDAO;

    public ServicioTecnicoControlador() {
        servicioTecnicoDAO = new ServicioTecnicoDAO();
    }

    /**
     * Metodo para obtener reportes de servicio técnico por marca
     * 
     * @param marca Nombre de la marca a filtrar
     * @return Lista de reportes de servicio técnico por marca
     * @throws Excepciones si el dato de marca es inválido o ocurre un error al obtener los reportes
     */
    public List<ReporteServicioTecnico> obtenerReportesPorMarca(String marca) throws Excepciones {
        if (!ValidacionDatos.esSoloTexto(marca)) {
            throw new Excepciones("Dato de marca vacio o inválido.");
        }

        return servicioTecnicoDAO.reportePorMarca(marca);
    }
    
}
