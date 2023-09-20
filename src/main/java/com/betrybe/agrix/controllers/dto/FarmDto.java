package com.betrybe.agrix.controllers.dto;

import com.betrybe.agrix.models.entities.FarmEntity;

/**
 * javadoc.
 */
public record FarmDto(Long id, String name, Double size) {
  public FarmEntity toFarm() {
    return new FarmEntity(id, name, size, null);
  }
}
