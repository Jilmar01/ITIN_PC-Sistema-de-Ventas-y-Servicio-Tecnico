package itin_pc.controller;

import itin_pc.dao.ProductoDAO;
import itin_pc.model.Categoria;
import itin_pc.model.Marca;
import itin_pc.model.Producto;
import itin_pc.util.Excepciones;
import itin_pc.util.ValidacionDatos;
import java.util.List;

public class ProductoControlador {

    private ProductoDAO productoDAO;

    public ProductoControlador() {
        this.productoDAO = new ProductoDAO();
    }
    
    /**
     * Metodo para obtener todos los productos
     * 
     * @return Lista de productos disponibles
     * @throws Excepciones si ocurre un error al obtener los productos
     */
    public List<Producto> obtenerProductos() throws Excepciones {
        return productoDAO.obtenerProductos();
    }

    /**
     * Metodo para agregar un producto
     * 
     * @param producto Objeto Producto con los datos del producto
     * @param cantidad Cantidad de unidades del producto a agregar
     * @return ID del producto agregado
     * @throws Excepciones si los datos del producto son inválidos o la cantidad es menor o igual a cero
     */
    public int agregarProducto(Producto producto, int cantidad) throws Excepciones {
        if (!ValidacionDatos.esSoloTexto(producto.getNombre())
                || !ValidacionDatos.esTextoGeneral(producto.getDescripcion())
                || !ValidacionDatos.esDecimal(producto.getPrecio())) {
            throw new Excepciones("Los datos del producto estan incompletos o son inválidos.");
        }
        if (!ValidacionDatos.esEntero(producto.getMarcaId())
                || !ValidacionDatos.esEntero(producto.getCategoriaId())) {
            throw new Excepciones("La marca o categoría del producto no son válidas.");
        }
        
        if(cantidad <= 0) {
            throw new Excepciones("La cantidad del producto debe ser mayor a cero.");
        }

        int producto_id = productoDAO.insertarProducto(producto);

        if (ValidacionDatos.esEntero(producto_id)) {
            productoDAO.insertarStock(producto_id, cantidad);
        }

        return producto_id;
    }

    /**
     * Metodo para actualizar un producto y su stock
     * 
     * @param producto Objeto Producto con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     * @throws Excepciones si los datos del producto son inválidos o el ID no es válido
     */
    public boolean actualizarProducto(Producto producto) throws Excepciones {
        
        if(!ValidacionDatos.esEntero(producto.getId())) {
            throw new Excepciones("El ID del producto no es válido.");
        }
        
        if (!ValidacionDatos.esSoloTexto(producto.getNombre())
                || !ValidacionDatos.esTextoGeneral(producto.getDescripcion())
                || !ValidacionDatos.esDecimal(producto.getPrecio())) {
            throw new Excepciones("Los datos del producto estan incompletos o son inválidos.");
        }
        if (!ValidacionDatos.esEntero(producto.getMarcaId())
                || !ValidacionDatos.esEntero(producto.getCategoriaId())) {
            throw new Excepciones("La marca o categoría del producto no son válidas.");
        }

        return productoDAO.actualizarProducto(producto);
    }

    /**
     * Metodo para eliminar un producto
     * 
     * @param productoId ID del producto a eliminard
     * @return true si la eliminación fue exitosa, false en caso contrario
     * @throws Excepciones si el ID del producto no es válido o la eliminación falla
     */
    public boolean eliminarProducto(int productoId) throws Excepciones {
        if (!ValidacionDatos.esEntero(productoId)) {
            throw new Excepciones("El ID del producto no es válido.");
        }
        return productoDAO.eliminarProducto(productoId);
    }

    /**
     * Metodo para obtener las categorías de productos
     * @return Lista de categorías disponibles
     * @throws Excepciones si ocurre un error al obtener las categorías
     */
    public List<Categoria> obtenerCategorias() throws Excepciones {
        return productoDAO.obtenerCategorias();
    }

    /**
     * Metodo para obtener productos por categoría
     * 
     * @param categoria Nombre de la categoría a filtrar
     * @return Lista de productos que pertenecen a la categoría especificada
     * @throws Excepciones si la categoría no es válida 
     */
    public List<Producto> obtenerProductosPorCategoria(String categoria) throws Excepciones {
        if (!ValidacionDatos.esSoloTexto(categoria)) {
            throw new Excepciones("La categoría no es válida.");
        }
        return productoDAO.obtenerProductosPorCategoria(categoria);
    }

    /**
     * Metodo para obtener las marcas de productos
     * 
     * @return Lista de marcas disponibles
     * @throws Excepciones si ocurre un error al obtener las marcas
     */
    public List<Marca> obtenerMarcas() throws Excepciones {
        return productoDAO.obtenerMarcas();
    }
}
