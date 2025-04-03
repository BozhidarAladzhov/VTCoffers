package com.example.vehicletransportcalculator.VTCoffers.web;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.example.vehicletransportcalculator.VTCoffers.service.OfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("offer")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @PostMapping
    public ResponseEntity <OfferDTO> createOffer (AddOfferDTO addOfferDTO){
            offerService.createOffer(addOfferDTO);
            return ResponseEntity.ok().build();

    }





}
