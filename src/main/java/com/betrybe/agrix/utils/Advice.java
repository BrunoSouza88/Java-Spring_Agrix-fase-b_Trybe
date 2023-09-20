package com.betrybe.agrix.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * javadoc.
 */
@RestControllerAdvice
public class Advice {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handlerFarmNotFound(RuntimeException exceptions) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  @ExceptionHandler(ExceptionCrop.class)
  public ResponseEntity<String> handlerCropNotFound(RuntimeException exceptions) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plantação não encontrada!");
  }

  @ExceptionHandler(ExceptionFertilizer.class)
  public ResponseEntity<String> handlerFertilizerNotFound(RuntimeException exceptions) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");
  }

}