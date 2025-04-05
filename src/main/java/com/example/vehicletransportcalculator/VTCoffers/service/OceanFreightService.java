package com.example.vehicletransportcalculator.VTCoffers.service;


import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class OceanFreightService {

    public BigDecimal calculateOceanFreight(PortOfLoadingEnum loading, PortOfDischargeEnum discharge) {
        BigDecimal baseRate = BigDecimal.ZERO;

        if (loading == null || discharge == null) {
            return baseRate;
        }

        switch (loading) {
            case NEW_YORK, SAVANNAH -> baseRate = new BigDecimal("1100");
            case LOS_ANGELES -> baseRate = new BigDecimal("1600");
            case SAN_FRANCISCO -> baseRate = new BigDecimal("1700");
            case HOUSTON -> baseRate = new BigDecimal("1200");
            case INDIANAPOLIS -> baseRate = new BigDecimal("1400");
        }

        // Modify based on discharge port
        switch (discharge) {
            case ROTTERDAM -> baseRate = baseRate.add(new BigDecimal("0"));
            case VARNA -> baseRate = baseRate.add(new BigDecimal("500"));
        }

        // Add price factor if needed
        // BigDecimal priceFactor = new BigDecimal(price).multiply(new BigDecimal("0.05"));

        return baseRate;
    }





}
