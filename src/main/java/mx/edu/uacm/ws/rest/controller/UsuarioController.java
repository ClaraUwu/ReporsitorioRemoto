package mx.edu.uacm.ws.rest.controller;

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
	public ResponseEntity<UsuarioBean> registrarUsuario(@RequestBody UsuarioBean usuarioBean ) {
		UsuarioBean creado = service.registrarUsuario(usuarioBean);
		return ResponseEntity.status(HttpStatus.CREATED).body(creado);
	}
	
      /**
       * Busca un usuario por email.
       *
       * @param email Email a buscar.
       * @return Usuario encontrado o vacío.
       */
	@GetMapping("/{email}")
	public ResponseEntity<?> buscarUsuario(@PathVariable String email){
		return service.buscarPorEmail(email)
				.<ResponseEntity<?>>map(ResponseEntity::ok)
				.orElseGet(()->ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Usuario No Encontrado. Intente Registrarse."));
	}
	
}
