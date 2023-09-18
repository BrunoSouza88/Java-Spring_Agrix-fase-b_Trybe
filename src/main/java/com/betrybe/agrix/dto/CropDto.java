package com.betrybe.agrix.dto;

import com.betrybe.agrix.entities.EntityCrop;
import com.betrybe.agrix.entities.EntityFarm;
import java.time.LocalDate;

/**
 * javadoc.
 */
public record CropDto(
    Long id,
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId) {
  public EntityCrop toCrop(EntityFarm farmId) {
    return new EntityCrop(id, name, plantedArea, farmId, plantedDate, harvestDate);
  }
}
