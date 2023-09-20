package com.betrybe.agrix.controllers;

import com.betrybe.agrix.controllers.dto.CropDto;
import com.betrybe.agrix.controllers.dto.FarmDto;
import com.betrybe.agrix.controllers.dto.ModelDtoConverter;
import com.betrybe.agrix.models.entities.FarmEntity;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FarmService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
@RequestMapping(value = "/farms")
public class FarmControllers {
  @Autowired
  private final FarmService farmService;
  private final CropService cropService;

  @Autowired
  public FarmControllers(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
   * javadoc.
   */
  @PostMapping
  public ResponseEntity<FarmEntity> createFarm(@RequestBody FarmDto newFarm) {
    FarmEntity farm = ModelDtoConverter.dtoToModel(newFarm);
    return ResponseEntity.status(HttpStatus.CREATED).body(farmService.insertFarm(newFarm.toFarm()));
  }

  /**
   * javadoc.
   */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<FarmEntity> allFarms = farmService.getAllFarms();
    return allFarms.stream()
        .map((farm) -> new FarmDto(farm.getId(), farm.getName(), farm.getSize()))
        .collect(Collectors.toList());
  }

  /**
   * javadoc.
   */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    FarmEntity response = farmService.getFarmById(id);
    return ResponseEntity.status(HttpStatus.OK).body(ModelDtoConverter.modelToDto(response));
  }

  /**
   * javadoc.
   */
  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropById(@PathVariable Long farmId) {
    Optional<FarmEntity> farm = Optional.ofNullable(farmService.getFarmById(farmId));
    if (farm.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(cropService.getCropsByFarmId(farmId));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }

  /**
   * javadoc.
   */
  @PostMapping("/{farmId}/crops")
  public ResponseEntity<?> insertCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {
    FarmEntity farm = farmService.getFarmById(farmId);
    Optional<FarmEntity> optionalFarm = Optional.ofNullable(farmService.getFarmById(farmId));

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(cropService.insertOneCrop(cropDto, optionalFarm.get()));
  }

}
