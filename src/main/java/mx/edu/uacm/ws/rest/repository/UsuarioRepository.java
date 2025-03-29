package mx.edu.uacm.ws.rest.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.uacm.ws.rest.bean.UsuarioBean;

public interface UsuarioRepository extends JpaRepository<UsuarioBean, Long>{
	
	Optional<UsuarioBean> findByEmail(String email);

}
