package org.example.bytebuilderspozzeroterigaldotheiler.error;

public class ErrorAtributo {

	private String atributo;
	private String error;


	public ErrorAtributo(String atributo, String error) {
		this.atributo = atributo;
		this.error = error;
	}


	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}