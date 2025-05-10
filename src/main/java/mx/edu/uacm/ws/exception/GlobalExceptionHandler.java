package mx.edu.uacm.ws.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(ValidacionException.class)
	public ResponseEntity<String> handleValidacionException(ValidacionException ex){
		return ResponseEntity.badRequest().body("Validacion Fallida: "+ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex){
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							 .body("Error Inesperado: "+ex.getMessage());
		
	}

}
