package mx.edu.uacm.ws;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
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
    private UsuarioDaoService usuarioDaoService;

    @Test
    @DisplayName("Registrar usuario con datos validos")
    void registrarUsuarioConDatosValidos() {
        UsuarioBean usuario = new UsuarioBean(null, "usuario@ejemplo.com", "ContrasenaSegura1");
        when(usuarioRepository.save(any(UsuarioBean.class))).thenReturn(usuario);

        UsuarioBean resultado = usuarioDaoService.registrarUsuario(usuario);

        assertNotNull(resultado);
        assertEquals("usuario@ejemplo.com", resultado.getEmail());
    }

    @Test
    @DisplayName("Registrar usuario con email invalido lanza excepcion")
    void registrarUsuarioConEmailInvalido() {
        UsuarioBean usuario = new UsuarioBean(null, "correoSinArroba", "Contrasena123");

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> usuarioDaoService.registrarUsuario(usuario)
        );

        assertEquals("Email invalido", excepcion.getMessage());
    }

    @Test
    @DisplayName("Registrar usuario con contrasena corta lanza excepcion")
    void registrarUsuarioConContrasenaInvalida() {
        UsuarioBean usuario = new UsuarioBean(null, "usuario@ejemplo.com", "123");

        IllegalArgumentException excepcion = assertThrows(
            IllegalArgumentException.class,
            () -> usuarioDaoService.registrarUsuario(usuario)
        );

        assertEquals("La contrasena debe tener al menos 8 caracteres", excepcion.getMessage());
    }

    @Test
    @DisplayName("Buscar usuario por email existente")
    void buscarUsuarioExistentePorEmail() {
        UsuarioBean usuario = new UsuarioBean(1L, "usuario@ejemplo.com", "Password123");
        when(usuarioRepository.findByEmail("usuario@ejemplo.com")).thenReturn(Optional.of(usuario));

        Optional<UsuarioBean> resultado = usuarioDaoService.buscarPorEmail("usuario@ejemplo.com");

        assertTrue(resultado.isPresent());
        assertEquals("usuario@ejemplo.com", resultado.get().getEmail());
    }

    @Test
    @DisplayName("Buscar usuario por email inexistente retorna vacio")
    void buscarUsuarioInexistentePorEmail() {
        when(usuarioRepository.findByEmail("noexiste@ejemplo.com")).thenReturn(Optional.empty());

        Optional<UsuarioBean> resultado = usuarioDaoService.buscarPorEmail("noexiste@ejemplo.com");

        assertFalse(resultado.isPresent());
    }
}
