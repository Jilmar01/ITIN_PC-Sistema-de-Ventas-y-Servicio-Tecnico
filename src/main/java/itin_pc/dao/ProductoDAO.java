package itin_pc.dao;

import itin_pc.model.Categoria;
import itin_pc.model.Marca;
import itin_pc.model.Producto;
import itin_pc.util.ConexionBD;
import itin_pc.util.Excepciones;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    private final Connection conexion;

    public ProductoDAO() {
        conexion = ConexionBD.obtenerConexion();
    }

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return Lista de productos
     * @throws Excepciones si ocurre un error al obtener los productos.
     */
    public List<Producto> obtenerProductos() throws Excepciones {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos";

        try (PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("producto_id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setMarcaId(rs.getInt("marca_id"));
                producto.setCategoriaId(rs.getInt("categoria_id"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getDouble("precio"));

                productos.add(producto);
            }
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return productos;
    }

    /**
     * Inserta un nuevo producto en la base de datos.
     *
     * @param producto Objeto Producto con los datos a insertar.
     * @return ID del producto insertado, o -1 si no se pudo insertar.
     * @throws Excepciones si ocurre un error al insertar el producto.
     */
    public int insertarProducto(Producto producto) throws Excepciones {

        String sql = "INSERT INTO productos (nombre, marca_id, categoria_id, descripcion, precio) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getMarcaId());
            ps.setInt(3, producto.getCategoriaId());
            ps.setString(4, producto.getDescripcion());
            ps.setDouble(5, producto.getPrecio());

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
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return -1;
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param producto_id ID del producto a buscar.
     * @return Producto con los datos del producto encontrado, o null si no
     * existe.
     * @throws Excepciones si ocurre un error al obtener el producto.
     */
    public Producto obtenerProductoPorId(int producto_id) throws Excepciones {

        String sql = "SELECT * FROM productos WHERE producto_id = ?";
        Producto producto = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, producto_id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId(rs.getInt("producto_id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setMarcaId(rs.getInt("marca_id"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return producto;
    }

    /**
     * Actualiza un producto en la base de datos.
     *
     * @param producto Objeto Producto con los nuevos datos.
     * @return true si se actualiza correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al actualizar el producto.
     */
    public boolean actualizarProducto(Producto producto) throws Excepciones {
        String sql = "UPDATE productos SET nombre = ?, marca_id = ?, categoria_id = ?, descripcion = ?, precio = ? WHERE producto_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, producto.getNombre());
            ps.setInt(2, producto.getMarcaId());
            ps.setInt(3, producto.getCategoriaId());
            ps.setString(4, producto.getDescripcion());
            ps.setDouble(5, producto.getPrecio());
            ps.setInt(6, producto.getId());

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param idProducto ID del producto a eliminar.
     * @return true si se elimina correctamente, false en caso contrario.
     * @throws Excepciones si ocurre un error al eliminar el producto.
     */
    public boolean eliminarProducto(int idProducto) throws Excepciones {

        String sql = "DELETE FROM productos WHERE producto_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

    /**
     * Verifica si un producto existe por su ID.
     *
     * @param idProducto ID del producto a verificar.
     * @return true si el producto existe, false en caso contrario.
     * @throws Excepciones si ocurre un error al verificar la existencia del
     * producto.
     */
    public boolean existeProducto(int idProducto) throws Excepciones {
        String sql = "SELECT COUNT(*) FROM productos WHERE producto_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idProducto);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return false;
    }

    //METODOS CON CATEGORIA
    /**
     * Obtiene una lista de todas las categorías.
     *
     * @return Lista de categorías.
     * @throws Excepciones si ocurre un error al obtener las categorías.
     */
    public List<Categoria> obtenerCategorias() throws Excepciones {

        String sql = "SELECT * FROM categorias";
        List<Categoria> categorias = new ArrayList<>();

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(rs.getInt("categoria_id"));
                    categoria.setNombre(rs.getString("nombre"));
                    categorias.add(categoria);
                }

                return categorias;
            }

        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

    /**
     * Obtiene una lista de productos por categoría.
     *
     * @param categoria Nombre de la categoría a filtrar.
     * @return Lista de productos que pertenecen a la categoría especificada.
     * @throws Excepciones si ocurre un error al obtener los productos.
     */
    public List<Producto> obtenerProductosPorCategoria(String categoria) throws Excepciones {

        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM productos WHERE categoria_id = (SELECT categoria_id FROM categorias WHERE nombre = ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setString(1, categoria);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId(rs.getInt("producto_id"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setMarcaId(rs.getInt("marca_id"));
                    producto.setCategoriaId(rs.getInt("categoria_id"));
                    producto.setDescripcion(rs.getString("descripcion"));
                    producto.setPrecio(rs.getDouble("precio"));

                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return productos;
    }

    /**
     * Obtiene una lista de todas las marcas.
     *
     * @return Lista de marcas.
     * @throws Excepciones si ocurre un error al obtener las marcas.
     */
    public List<Marca> obtenerMarcas() throws Excepciones {

        String sql = "SELECT * FROM marcas";
        List<Marca> marcas = null;

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {

                marcas = new ArrayList<>();
                while (rs.next()) {
                    Marca marca = new Marca();
                    marca.setId(rs.getInt("marca_id"));
                    marca.setNombre(rs.getString("nombre"));
                    marcas.add(marca);
                }

                return marcas;
            }

        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

    /**
     * Actualiza el stock de un producto.
     *
     * @param productoId ID del producto a actualizar.
     * @param cantidad Cantidad a agregar al stock.
     * @return true si se actualiza correctamente, false en caso contrario.
     * @throws Excepciones
     */
    public boolean actualizarStock(int productoId, int cantidad) throws Excepciones {
        String sql = "UPDATE stock SET cantidad = ? WHERE producto_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, productoId);

            int filas = ps.executeUpdate();
            return filas > 0;
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

    /**
     * Inserta un nuevo registro de stock para un producto.
     *
     * @param producto_id ID del producto al que se le agrega el stock.
     * @param cantidad Cantidad de stock a agregar.
     * @return ID del registro de stock insertado, o -1 si no se pudo insertar.
     * @throws Excepciones si ocurre un error al insertar el stock.
     */
    public int insertarStock(int producto_id, int cantidad) throws Excepciones {

        String sql = "INSERT INTO stock (cantidad, producto_id) VALUES (?, ?)";

        try (PreparedStatement ps = conexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, cantidad);
            ps.setInt(2, producto_id);

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
            throw new Excepciones("ProductoDAO", e.getMessage());
        }

        return -1;
    }

    /**
     * Obtiene el stock actual de un producto por su ID.
     *
     * @param productoId ID del producto.
     * @return Cantidad de stock disponible, o -1 si no existe.
     * @throws Excepciones si ocurre un error al consultar el stock.
     */
    public int obtenerStockPorProductoId(int productoId) throws Excepciones {
        String sql = "SELECT cantidad FROM stock WHERE producto_id = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, productoId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("cantidad");
                }
            }
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
        return -1;
    }

    public boolean eliminarStock(int productoId) throws Excepciones {
        String sql = "DELETE FROM stock WHERE producto_id = ?";

        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, productoId);

            int filas = ps.executeUpdate();
            return filas > 0; 
        } catch (SQLException e) {
            throw new Excepciones("ProductoDAO", e.getMessage());
        }
    }

}
