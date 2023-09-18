package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.dto.DtoConverter;
import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.entities.EntityFarm;
import com.betrybe.agrix.services.CropService;
import com.betrybe.agrix.services.FarmService;
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
  private FarmService farmService;
  private CropService cropService;

  @Autowired
  public FarmControllers(FarmService farmService, CropService cropService) {
    this.farmService = farmService;
    this.cropService = cropService;
  }

  /**
 * javadoc.
 */

  @PostMapping()
  public ResponseEntity<EntityFarm> createFarm(@RequestBody FarmDto newFarm) {
    EntityFarm farm = DtoConverter.dtoToModel(newFarm);

    EntityFarm newFarmInserted = farmService.insertFarm(newFarm.toFarm());
    return ResponseEntity.status(HttpStatus.CREATED).body(newFarmInserted);
  }

  /**
 * javadoc.
 */
  @GetMapping("/{id}")
  public ResponseEntity<FarmDto> getFarmById(@PathVariable Long id) {
    EntityFarm response = farmService.getFarmbyId(id);
    return ResponseEntity.status(HttpStatus.OK).body(DtoConverter.modelToDto(response));
  }

  /**
 * javadoc.
 */
  @GetMapping()
  public List<FarmDto> getAllFarms() {
    List<EntityFarm> allFarms = farmService.getAllFarms();

    return allFarms.stream()
    .map((farm) -> new FarmDto(
      farm.getId(),
      farm.getName(),
      farm.getSize()))
      .collect(Collectors.toList());
  }

  /**
 * javadoc.
 */

  @PostMapping("/{farmId}/crops")
  public ResponseEntity<?> insertCrop(@PathVariable Long farmId, @RequestBody CropDto cropDto) {


    EntityFarm farmById = farmService.getFarmbyId(farmId);
    Optional<EntityFarm> optionalFarm = Optional.ofNullable(farmById);

    if (optionalFarm.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }

    return ResponseEntity.status(HttpStatus.CREATED)
      .body(cropService.insertCrop(cropDto, optionalFarm.get()));
  }

  /**
  * javadoc.
  */

  @GetMapping("/{farmId}/crops")
  public ResponseEntity<?> getCropById(@PathVariable Long farmId) {
    Optional<EntityFarm> farm = Optional.ofNullable(farmService.getFarmbyId(farmId));
    if (farm.isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(cropService.getCropsByFarmId(farmId));
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
  }
}
