package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;

import java.util.List;


public interface OfferService {

    OfferDTO createOffer (AddOfferDTO addOfferDTO);

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(Long id);

    void deleteOffer (Long offerId);


}
