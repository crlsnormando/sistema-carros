package br.com.carro.exception;

public class ErroSistema extends Exception{
	

	private static final long serialVersionUID = 1L;

	public ErroSistema( String message) {
		super(message);
	}
	
	public ErroSistema(String message, Throwable cause) {
		super(message, cause);
		
	}
	
	

}
