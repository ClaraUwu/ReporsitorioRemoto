package mx.edu.uacm.ws.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import mx.edu.uacm.ws.rest.bean.ProductoBean;

public interface ProductoRepository extends JpaRepository<ProductoBean, Long> {

}
