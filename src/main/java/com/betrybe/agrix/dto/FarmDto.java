package com.betrybe.agrix.dto;

import com.betrybe.agrix.entities.EntityFarm;

/**
 * javadoc.
 */
public record FarmDto(Long id, String name, Double size) {
  public EntityFarm toFarm() {
    return new EntityFarm(id, name, size, null);
  }
}
