package mx.edu.uacm.ws.rest.dao;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioDaoService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsuarioBean registrarUsuario(UsuarioBean usuario) {
		logger.debug("Inicio de registrarUsuario() con email: {}", usuario.getEmail());

		try {
			if (usuario == null || usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
				logger.warn("Intento de registro con email inválido: {}", usuario != null ? usuario.getEmail() : "null");
				throw new IllegalArgumentException("Email invalido");
			}

			if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
				logger.warn("Intento de registro con contraseña inválida para email: {}", usuario.getEmail());
				throw new IllegalArgumentException("La contrasena debe tener al menos 8 caracteres");
			}

			usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
			UsuarioBean creado = usuarioRepository.save(usuario);

			logger.info("Usuario registrado exitosamente con email: {}", creado.getEmail());
			return creado;

		} catch (Exception e) {
			logger.error("Error inesperado en registrarUsuario(): {}", e.getMessage(), e);
			throw e;
		} finally {
			logger.debug("Fin de registrarUsuario()");
		}
	}

	public Optional<UsuarioBean> buscarPorEmail(String email) {
		logger.debug("Inicio de buscarPorEmail() con email: {}", email);
		Optional<UsuarioBean> resultado = usuarioRepository.findByEmail(email);
		logger.debug("Fin de buscarPorEmail() con resultado: {}", resultado.isPresent() ? "ENCONTRADO" : "NO ENCONTRADO");
		return resultado;
	}
	
}
