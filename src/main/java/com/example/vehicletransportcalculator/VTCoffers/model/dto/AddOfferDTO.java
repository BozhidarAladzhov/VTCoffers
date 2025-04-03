package com.example.vehicletransportcalculator.VTCoffers.model.dto;



import com.example.vehicletransportcalculator.VTCoffers.model.enums.EngineTypeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;


public record AddOfferDTO(
        Long id,
    String description,
    PortOfLoadingEnum portOfLoading,
    PortOfDischargeEnum portOfDischarge,
    EngineTypeEnum engineType,
    Integer price
) {



}
