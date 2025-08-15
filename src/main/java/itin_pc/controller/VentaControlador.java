package itin_pc.controller;

import itin_pc.dao.DetallesVentaDAO;
import itin_pc.dao.VentaDAO;
import itin_pc.model.DetalleVenta;
import itin_pc.model.Venta;
import java.util.List;

import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class VentaControlador {

    private VentaDAO ventaDAO;
    private DetallesVentaDAO detallesVentaDAO;

    public VentaControlador() {
        ventaDAO = new VentaDAO();
        detallesVentaDAO = new DetallesVentaDAO();
    }

    /**
     * Registra una nueva venta con sus detalles.
     * 
     * @param venta Objeto Venta que contiene la información de la venta.
     * @param detalles Lista de objetos DetalleVenta que contienen los detalles de la venta.
     * @return El ID de la venta registrada.
     * @throws Excepciones Si ocurre un error durante el registro de la venta o si los datos son inválidos.
     */
    public int registrarVenta(Venta venta, List<DetalleVenta> detalles) throws Excepciones {

        if (detalles.isEmpty()) {
            throw new Excepciones("Detalle de venta no puede estar vacío");
        }

        if (!ValidacionDatos.esEntero(venta.getEmpleadoId())
                || !ValidacionDatos.esEntero(venta.getClienteId())
                || !ValidacionDatos.esEntero(venta.getFormaPagoId())) {
            throw new Excepciones("Datos de venta vacios o inválidos");
        }

        int ventaId = ventaDAO.insertarVenta(venta);
        
        for(int i = 0; i < detalles.size(); i++) {
            DetalleVenta detalle = detalles.get(i);
            detalle.setVentaId(ventaId);
            int detalleId = detallesVentaDAO.insertarDetallesVenta(detalle);
            if(detalleId == -1) {
                throw new Excepciones("Error al insertar detalle de venta");
            }
        }
        
        return ventaId;
    }


    /**
     * Obtiene una venta por su ID.
     *
     * @param id El ID de la venta a obtener.
     * @return Un objeto Venta con los datos de la venta, o null si no se encuentra.
     * @throws Excepciones si ocurre un error al obtener la venta o si el ID es inválido.
     */
    public Venta obtenerVentaPorId(int id) throws Excepciones {

        if (id <= 0) {
            throw new Excepciones("ID de venta inválido");
        }

        return ventaDAO.obtenerVentaPorId(id);
    }
}
