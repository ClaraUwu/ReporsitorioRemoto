package mx.edu.uacm.ws;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import mx.edu.uacm.ws.rest.bean.UsuarioBean;
import mx.edu.uacm.ws.rest.dao.UsuarioDaoService;
import mx.edu.uacm.ws.rest.repository.UsuarioRepository;

@SpringBootTest
class ECommercePlatformApplicationTests {

	@Mock
	private UsuarioRepository usuarioRepository;
	
	@InjectMocks
	private UsuarioDaoService service;
	
	@Test
	void registrarUsuario() {
		UsuarioBean usuarioBean = new UsuarioBean(null, "test@email.com", "password123");
		when(usuarioRepository.save(any())).thenReturn(usuarioBean);
		UsuarioBean resultado = service.registrarUsuario(usuarioBean);
		assertEquals("test@email.com", resultado.getEmail());
		
	}

}
