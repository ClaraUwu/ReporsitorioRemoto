package mx.edu.uacm.ws.rest.dao;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.edu.uacm.ws.rest.bean.UsuarioBean;
import mx.edu.uacm.ws.rest.repository.UsuarioRepository;

@Component
public class UsuarioDaoService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioBean registrarUsuario(UsuarioBean usuario) {
		return usuarioRepository.save(usuario);	
	}
	
	public Optional<UsuarioBean> buscarPorEmail(String email){
		return usuarioRepository.findByEmail(email);
	}

}
