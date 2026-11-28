package TUTI.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import TUTI.excepciones.Excepcion;
import jakarta.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class ExceptionHandler {

   @org.springframework.web.bind.annotation.ExceptionHandler(Excepcion.class)
   public ResponseEntity<ErrorAtributo> methodArgumentNotValidException(HttpServletRequest request, Excepcion e) {
	   return  ResponseEntity.status(e.getStatusCode()).body(new ErrorAtributo(e.getAtributo(),e.getMensaje()));
   }
}