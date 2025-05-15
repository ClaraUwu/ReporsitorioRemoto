package mx.edu.uacm.ws.rest.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.ws.rest.bean.ProductoBean;
import mx.edu.uacm.ws.rest.dao.ProductoDaoService;

/**
 * Controlador REST para las operaciones con productos.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

	private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

	private final ProductoDaoService service;

	public ProductoController(ProductoDaoService service) {
	    this.service = service;
	}

	@PostMapping
	public ResponseEntity<ProductoBean> crearProducto(@RequestBody ProductoBean producto) {
	    logger.debug("Solicitud POST /api/productos recibida");
	    ProductoBean creado = service.crearProducto(producto);
	    logger.debug("Respuesta enviada con producto creado");
	    return ResponseEntity.ok(creado);
	}

	@GetMapping
	public ResponseEntity<List<ProductoBean>> listarProductos() {
	    logger.debug("Solicitud GET /api/productos recibida");
	    List<ProductoBean> productos = service.listarProductos();
	    logger.debug("Respuesta enviada con {} productos", productos.size());
	    return ResponseEntity.ok(productos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerProducto(@PathVariable Long id) {
	    logger.debug("Solicitud GET /api/productos/{} recibida", id);
	    Optional<ProductoBean> producto = service.buscarPorId(id);

	    if (producto.isPresent()) {
	        logger.info("Producto encontrado con ID: {}", id);
	        return ResponseEntity.ok(producto.get());
	    } else {
	        logger.warn("Producto no encontrado con ID: {}", id);
	        return ResponseEntity.status(404).body("Producto no encontrado");
	    }
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarProducto(@PathVariable Long id) {
	    logger.debug("Solicitud DELETE /api/productos/{} recibida", id);
	    try {
	        service.eliminarProducto(id);
	        return ResponseEntity.ok("Producto eliminado");
	    } catch (IllegalArgumentException e) {
	        logger.warn("Eliminar falló: {}", e.getMessage());
	        return ResponseEntity.status(404).body(e.getMessage());
	    }
	}
	
}
