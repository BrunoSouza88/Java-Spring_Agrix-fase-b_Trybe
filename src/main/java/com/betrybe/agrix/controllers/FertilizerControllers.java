package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.FertilizerDto;
import com.betrybe.agrix.controllers.dto.ModelDtoConverter;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.service.FertilizerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * javadoc.
 */
@RestController
@RequestMapping(value = "/fertilizers")
public class FertilizerControllers {
  @Autowired
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerControllers(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * javadoc.
   */
  @PostMapping
  public ResponseEntity<Fertilizer> insertFarm(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService
            .insertOneFertilizer(fertilizerDto.toFertilizer());
    return ResponseEntity.status(HttpStatus.CREATED).body((newFertilizer));
  }

  /**
   * javadoc.
   */
  @GetMapping()
  public ResponseEntity<List<Fertilizer>> getFarms() {
    return ResponseEntity.status(HttpStatus.OK).body(fertilizerService.getAllFertilizers());
  }

  /**
   * javadoc.
   */
  @GetMapping("/{fertilizerId}")
  public ResponseEntity<?> getFertilizerById(@PathVariable Long fertilizerId) {
    Optional<Fertilizer> fert = fertilizerService.getFertilizerById(fertilizerId);
    if (fert.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK)
          .body(ModelDtoConverter.modelToDtoFert(fert.get()));

    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fertilizante não encontrado!");

  }

}
