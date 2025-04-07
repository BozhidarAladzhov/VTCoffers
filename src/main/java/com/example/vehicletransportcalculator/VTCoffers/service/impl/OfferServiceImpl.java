package com.example.vehicletransportcalculator.VTCoffers.service.impl;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.example.vehicletransportcalculator.VTCoffers.service.OceanFreightService;
import com.example.vehicletransportcalculator.VTCoffers.service.OfferService;

import java.math.BigDecimal;
import java.util.List;

import com.example.vehicletransportcalculator.VTCoffers.service.exception.ObjectNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.stereotype.Service;


@Service
public class OfferServiceImpl implements OfferService {


    private final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);

    private final OfferRepository offerRepository;
    private final OceanFreightService oceanFreightService;

    public OfferServiceImpl(OfferRepository offerRepository, OceanFreightService oceanFreightService) {
        this.offerRepository = offerRepository;
        this.oceanFreightService = oceanFreightService;
    }


    @Override
    public OfferDTO createOffer(AddOfferDTO addOfferDTO) {
        OfferEntity offerEntity = new OfferEntity();
        offerEntity = offerRepository.save(map(addOfferDTO));

        BigDecimal freightCost = oceanFreightService.calculateOceanFreight(
                offerEntity.getPortOfLoading(),
                offerEntity.getPortOfDischarge()
        );

        return new OfferDTO(
                offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getPortOfLoading(),
                offerEntity.getPortOfDischarge(),
                offerEntity.getEngine(),
                offerEntity.getPrice(),
                freightCost
        );
    }

    @Override
    public OfferDTO getOfferById(Long id) {
        return offerRepository
                .findById(id)
                .map(this::map)
                .orElseThrow(ObjectNotFoundException::new);

    }


    @Override
    @PreAuthorize("@offerServiceImpl.isOwner(#userDetails, #offerId)")
    public void deleteOffer(UserDetails userDetails, Long offerId) {
        offerRepository.deleteById(offerId);
    }


    @Override
    public boolean isOwner(UserDetails userDetails, Long offerId) {
        //
        return true;
    }



    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(this::map)
                .toList();
    }


    private OfferDTO map(OfferEntity offerEntity) {
        BigDecimal freight = oceanFreightService.calculateOceanFreight(
                offerEntity.getPortOfLoading(),
                offerEntity.getPortOfDischarge()
        );

        return new OfferDTO(
                offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getPortOfLoading(),
                offerEntity.getPortOfDischarge(),
                offerEntity.getEngine(),
                offerEntity.getPrice(),
                freight
        );
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
