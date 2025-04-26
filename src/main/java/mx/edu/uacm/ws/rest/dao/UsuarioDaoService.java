package mx.edu.uacm.ws.rest.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.resilience4j.retry.annotation.Retry;
import mx.edu.uacm.ws.exception.ValidacionException;
import mx.edu.uacm.ws.rest.bean.UsuarioBean;
import mx.edu.uacm.ws.rest.repository.UsuarioRepository;

/**
 * Servicio DAO para la gestion de usuarios.
 */
@Component
public class UsuarioDaoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Retry(name = "registroUsuario", fallbackMethod = "fallbackRegistrar")
	public UsuarioBean registrarUsuario(UsuarioBean usuario) {
		 if (usuario == null || usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
	            throw new IllegalArgumentException("Email invalido");
	        }

        if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contrasena debe tener al menos 8 caracteres");
        }
	    
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
		return usuarioRepository.save(usuario);	
	}
	
	public UsuarioBean fallbackRegistrar(UsuarioBean usuario, Throwable e) {
	    throw new ValidacionException("No se pudo registrar el usuario. Intente mas tarde.");
	}
	
	public Optional<UsuarioBean> buscarPorEmail(String email){
		return usuarioRepository.findByEmail(email);
	}

}
