package mx.edu.uacm.ws.rest.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Entidad que representa a un usuario en la plataforma de e-commerce.
 */
@Entity
@Table(name="usuarios")
public class UsuarioBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Email(message = "El correo electronico debe ser valido")
    @NotBlank(message = "El correo electrónico no puede estar vacio")
	@Column(unique = true, nullable = false)
	private String email;
    
    @NotBlank(message = "La contrasena no puede estar vacia")
    @Size(min = 8, message = "La contrasena debe tener al menos 8 caracteres")
	@Column(nullable = false)
	private String password;
	
	public UsuarioBean() {
	}
	
	public UsuarioBean(Long id, String email, String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
}
