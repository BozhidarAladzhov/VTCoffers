package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;

import java.util.List;
import java.util.Optional;

public interface OfferService {

    void createOffer (AddOfferDTO addOfferDTO);

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(Long id);

    void deleteOffer (Long offerId);


}
