package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface OfferService {

    OfferDTO createOffer (AddOfferDTO addOfferDTO);

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(Long id);

    boolean isOwner(UserDetails userDetails, Long offerId);


    void deleteOffer(UserDetails userDetails, Long offerId);



}
