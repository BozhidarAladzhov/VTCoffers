package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;


public interface OfferService {

    OfferDTO createOffer (AddOfferDTO addOfferDTO);

    void deleteOffer(UserDetails userDetails, Long offerId);

    OfferDTO getOfferById(Long id);

    PagedModel<OfferDTO> getAllOffers(Pageable pageable);


    boolean isOwner(UserDetails userDetails, Long offerId);

}
