package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import org.hibernate.mapping.List;
import org.springframework.security.core.userdetails.UserDetails;


public interface OfferService {

    OfferDTO createOffer (AddOfferDTO addOfferDTO);

    void deleteOffer(UserDetails userDetails, Long offerId);

    OfferDTO getOfferById(Long id);

    List getAllOffers();

    boolean isOwner(UserDetails userDetails, Long offerId);

}
