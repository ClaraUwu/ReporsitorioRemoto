package mx.edu.uacm.ws.rest.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.edu.uacm.ws.rest.bean.UsuarioBean;
import mx.edu.uacm.ws.rest.dao.UsuarioDaoService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioDaoService service;

	public UsuarioController(UsuarioDaoService service) {
		this.service = service;
	} 
	
	@PostMapping("/registro")
	public UsuarioBean registrarUsuario(@RequestBody UsuarioBean usuarioBean ) {
		return service.registrarUsuario(usuarioBean);
	}
	
	@GetMapping("/{email}")
	public Optional<UsuarioBean> buscarUsuario(@PathVariable String email){
		return service.buscarPorEmail(email);
	}
	
}
