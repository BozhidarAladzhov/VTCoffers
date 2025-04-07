package com.example.vehicletransportcalculator.VTCoffers.model.dto;

import com.example.vehicletransportcalculator.VTCoffers.model.enums.EngineTypeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;

import java.math.BigDecimal;

public record OfferDTO(
        Long id,
        String description,
        PortOfLoadingEnum portOfLoading,
        PortOfDischargeEnum portOfDischarge,
        EngineTypeEnum engineType,
        Integer price,
        BigDecimal oceanFreight
) {



}
