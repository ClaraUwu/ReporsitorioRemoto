package mx.edu.uacm.ws.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@ExceptionHandler(ValidacionException.class)
	public ResponseEntity<String> handleValidacionException(ValidacionException ex) {
		logger.warn("Excepción de validación: {}", ex.getMessage());
		return ResponseEntity.badRequest().body("Validacion Fallida: " + ex.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
		logger.error("Error inesperado no controlado: {}", ex.getMessage(), ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error Inesperado: " + ex.getMessage());
	}

}
