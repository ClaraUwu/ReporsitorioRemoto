package mx.edu.uacm.ws.rest.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
	
	public Optional<UsuarioBean> buscarPorEmail(String email){
		return usuarioRepository.findByEmail(email);
	}

}
