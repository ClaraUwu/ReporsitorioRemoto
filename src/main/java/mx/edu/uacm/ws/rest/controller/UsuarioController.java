package mx.edu.uacm.ws.rest.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uacm.ws.rest.bean.UsuarioBean;
import mx.edu.uacm.ws.rest.dao.UsuarioDaoService;

/**
 * Controlador REST para las operaciones con usuarios.
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioDaoService service;

	public UsuarioController(UsuarioDaoService service) {
		this.service = service;
	} 
	
	/**
     * Registra un nuevo usuario.
     *
     * @param usuarioBean Objeto del usuario a registrar.
     * @return Usuario registrado o error.
     */
	@PostMapping("/registro")
	public ResponseEntity<UsuarioBean> registrarUsuario(@RequestBody UsuarioBean usuarioBean) {
		logger.debug("Solicitud POST /registro recibida");
		UsuarioBean creado = service.registrarUsuario(usuarioBean);
		logger.debug("Respuesta POST /registro enviada");
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}
	
      /**
       * Busca un usuario por email.
       *
       * @param email Email a buscar.
       * @return Usuario encontrado o vacío.
       */
	@GetMapping("/{email}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String email) {
		logger.debug("Solicitud GET /{} recibida", email);
		Optional<UsuarioBean> usuario = service.buscarPorEmail(email);

		if (usuario.isPresent()) {
			logger.info("Usuario consultado: {}", email);
			return ResponseEntity.ok(usuario.get());
		} else {
			logger.warn("Usuario no encontrado con email: {}", email);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario No Encontrado. Intente Registrarse.");
		}
	}
	
}
