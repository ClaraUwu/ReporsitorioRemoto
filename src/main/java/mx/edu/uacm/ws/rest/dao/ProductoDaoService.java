package mx.edu.uacm.ws.rest.dao;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import mx.edu.uacm.ws.rest.bean.ProductoBean;
import mx.edu.uacm.ws.rest.repository.ProductoRepository;

/**
 * Servicio DAO para la gestion de productos.
 */
@Component
public class ProductoDaoService {

	private static final Logger logger = LoggerFactory.getLogger(ProductoDaoService.class);

	private final ProductoRepository productoRepository;

	public ProductoDaoService(ProductoRepository productoRepository) {
	    this.productoRepository = productoRepository;
	}

	public ProductoBean crearProducto(ProductoBean producto) {
	    logger.debug("Inicio de crearProducto() con nombre: {}", producto.getNombre());

	    try {
	        if (productoRepository.existsById(producto.getId())) {
	            logger.warn("Intento de crear producto con ID existente: {}", producto.getId());
	            throw new IllegalArgumentException("Producto con ese ID ya existe");
	        }

	        ProductoBean guardado = productoRepository.save(producto);
	        logger.info("Producto creado correctamente: {}", guardado.getNombre());
	        return guardado;

	    } catch (Exception e) {
	        logger.error("Error inesperado en crearProducto(): {}", e.getMessage(), e);
	        throw e;
	    } finally {
	        logger.debug("Fin de crearProducto()");
	    }
	}

	public List<ProductoBean> listarProductos() {
	    logger.debug("Inicio de listarProductos()");
	    List<ProductoBean> productos = productoRepository.findAll();
	    logger.info("Se listaron {} productos", productos.size());
	    logger.debug("Fin de listarProductos()");
	    return productos;
	}

	public Optional<ProductoBean> buscarPorId(Long id) {
	    logger.debug("Inicio de buscarPorId() con ID: {}", id);
	    Optional<ProductoBean> producto = productoRepository.findById(id);
	    if (producto.isPresent()) {
	        logger.info("Producto encontrado con ID: {}", id);
	    } else {
	        logger.warn("Producto no encontrado con ID: {}", id);
	    }
	    logger.debug("Fin de buscarPorId()");
	    return producto;
	}

	public void eliminarProducto(Long id) {
	    logger.debug("Inicio de eliminarProducto() con ID: {}", id);
	    if (!productoRepository.existsById(id)) {
	        logger.warn("Intento de eliminar producto inexistente con ID: {}", id);
	        throw new IllegalArgumentException("Producto no encontrado");
	    }

	    productoRepository.deleteById(id);
	    logger.info("Producto eliminado con ID: {}", id);
	    logger.debug("Fin de eliminarProducto()");
	}
	
}
