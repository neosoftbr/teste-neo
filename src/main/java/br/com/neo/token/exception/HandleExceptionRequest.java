package br.com.neo.token.exception;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.neo.token.request.RequestError;
import br.com.neo.token.response.ResponseApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class HandleExceptionRequest {

  @Async
  @ExceptionHandler({ UsertException.class })
  public CompletableFuture<ResponseEntity<ResponseApi>> handleCustomException(UsertException exception) throws InterruptedException, ExecutionException {
    
    ArrayList<RequestError> errors = new ArrayList<>();

    for (String errorMsg : exception.getErrors()) {
      log.info("errors: "+errorMsg);
      errors.add(
        RequestError.builder().errorCode(exception.getErrorCode()).message(errorMsg).build()
      );
    }
    return CompletableFuture.completedFuture(
    ResponseEntity.status(exception.getReturnType()).body( ResponseApi
    .builder()
    .errors(errors)
    .id(exception.getId())
    .success(false)
    .build()));
  }



}
