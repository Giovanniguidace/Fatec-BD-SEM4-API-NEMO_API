package br.gov.sp.fatec.nemo.config;

import br.gov.sp.fatec.nemo.usecases.impls.dtos.DefaultError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class MessageConfiguration extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        DefaultError erro = new DefaultError(HttpStatus.BAD_GATEWAY.value(), e.getMessage());

        return new ResponseEntity(erro , HttpStatus.BAD_GATEWAY);
    }
}
