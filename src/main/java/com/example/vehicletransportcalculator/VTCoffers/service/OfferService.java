package com.example.vehicletransportcalculator.VTCoffers.service;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
<<<<<<< HEAD
import org.hibernate.mapping.List;
import org.springframework.security.core.userdetails.UserDetails;
=======
>>>>>>> parent of 4025fdc (Delete method)


public interface OfferService {

    OfferDTO createOffer (AddOfferDTO addOfferDTO);

    List<OfferDTO> getAllOffers();

    OfferDTO getOfferById(Long id);

<<<<<<< HEAD
    List getAllOffers();
=======
    void deleteOffer (Long offerId);

>>>>>>> parent of 4025fdc (Delete method)

}
