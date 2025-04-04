package com.example.vehicletransportcalculator.VTCoffers.model.entity;


import com.example.vehicletransportcalculator.VTCoffers.model.enums.EngineTypeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "offers")
public class OfferEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotEmpty
  private String description;

  @Enumerated(EnumType.STRING)
  private PortOfLoadingEnum portOfLoading;

  @Enumerated(EnumType.STRING)
  private PortOfDischargeEnum portOfDischarge;

  @Enumerated(EnumType.STRING)
  private EngineTypeEnum engine;

  @Positive
  private int price;

  public PortOfLoadingEnum getPortOfLoading() {
    return portOfLoading;
  }

  public OfferEntity setPortOfLoading(PortOfLoadingEnum portOfLoading) {
    this.portOfLoading = portOfLoading;
    return this;
  }

  public PortOfDischargeEnum getPortOfDischarge() {
    return portOfDischarge;
  }

  public OfferEntity setPortOfDischarge(PortOfDischargeEnum portOfDischarge) {
    this.portOfDischarge = portOfDischarge;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public OfferEntity setDescription(String description) {
    this.description = description;
    return this;
  }

  public EngineTypeEnum getEngine() {
    return engine;
  }

  public OfferEntity setEngine(EngineTypeEnum engine) {
    this.engine = engine;
    return this;
  }

  public int getPrice() {
    return price;
  }

  public OfferEntity setPrice(int price) {
    this.price = price;
    return this;
  }

  public Long getId() {
    return id;
  }

  public OfferEntity setId(Long id) {
    this.id = id;
    return this;
  }
}
