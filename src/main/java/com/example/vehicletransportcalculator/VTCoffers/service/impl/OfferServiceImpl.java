package com.example.vehicletransportcalculator.VTCoffers.service.impl;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.example.vehicletransportcalculator.VTCoffers.service.OfferService;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {


    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }


    @Override
    public void createOffer(AddOfferDTO addOfferDTO) {
        offerRepository.save(map(addOfferDTO));
    }

    private static OfferEntity map (AddOfferDTO addOfferDTO){

        return new OfferEntity()
                .setDescription(addOfferDTO.description())
                .setPortOfLoading(addOfferDTO.portOfLoading())
                .setPortOfDischarge(addOfferDTO.portOfDischarge())
                .setEngine(addOfferDTO.engineType())
                .setPrice(addOfferDTO.price());
    }


}
