package org.example.bytebuilderspozzeroterigaldotheiler.excepciones;

import org.example.bytebuilderspozzeroterigaldotheiler.error.ErrorAtributo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManejarExcepciones {

	@ExceptionHandler(Excepcion.class)
	public ResponseEntity<ErrorAtributo> manejarExcepcion(Excepcion e) {

		ErrorAtributo error =
				new ErrorAtributo(
						e.getAtributo(),
						e.getMensaje()
				);

		return ResponseEntity
				.status(e.getStatusCode())
				.body(error);
	}
}