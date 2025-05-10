package mx.edu.uacm.ws.exception;

public class ValidacionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ValidacionException(String mensaje) {
		super(mensaje);
	}

}
