package com.betrybe.agrix.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * javadoc.
 */
@Entity
@Table(name = "crops")
public class EntityCrop {
  
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id

  private Long id;

  private String name;

  @Column(name = "planted_area")
  private Double plantedArea;

  @ManyToOne
  @JoinColumn(name = "farm_id")
  @JsonIgnore
  private EntityFarm farmId;

  @Column(name = "planted_date")
  private LocalDate plantedDate;

  @Column(name = "harvest_date")
  private LocalDate harvestDate;

  public EntityCrop() {}

  /**
* javadoc.
*/

  public EntityCrop(
          Long id,
          String name,
          Double plantedArea,
          EntityFarm farmId,
          LocalDate plantedDate,
          LocalDate harvestDate) {
    this.id = id;
    this.name = name;
    this.plantedArea = plantedArea;
    this.farmId = farmId;
    this.plantedDate = plantedDate;
    this.harvestDate = harvestDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long newId) {
    this.id = newId;
  }

  public String getName() {
    return name;
  }

  public void setName(String newName) {
    this.name = newName;
  }

  public Double getPlantedArea() {
    return this.plantedArea;
  }

  public void setPlantedArea(Double newPlantedArea) {
    this.plantedArea = newPlantedArea;
  }

  public EntityFarm getFarmId() {
    return farmId;
  }

  public void setFarmId(EntityFarm newFarmId) {
    this.farmId = newFarmId;
  }

  public LocalDate getPlantedDate() {
    return plantedDate;
  }

  public void setPlantedDate(LocalDate newPlantedDate) {
    this.plantedDate = newPlantedDate;
  }

  public LocalDate getHarvestDate() {
    return harvestDate;
  }

  public void setHarvestDate(LocalDate newHarvestDate) {
    this.harvestDate = newHarvestDate;
  }
}
