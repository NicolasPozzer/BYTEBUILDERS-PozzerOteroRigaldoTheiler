package org.example.bytebuilderspozzeroterigaldotheiler.excepciones;

public class Excepcion extends Exception {

	private String atributo;
	private String mensaje;
	private int statusCode;


	public Excepcion() {
		super();
	}


	public Excepcion(String atributo, String mensaje, int statusCode) {

		super(mensaje);

		this.atributo = atributo;
		this.mensaje = mensaje;
		this.statusCode = statusCode;
	}


	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public String getMessage() {
		return mensaje;
	}
}