package com.example.vehicletransportcalculator.VTCoffers.service.impl;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.example.vehicletransportcalculator.VTCoffers.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    OfferDTO getOfferById(Long id) {
        return offerRepository
                .findById(id)
                .map(OfferServiceImpl::map)
                .orElseThrow(() -> new IllegalArgumentException("Not found!"));

    }


    @Override
    public void deleteOffer(Long offerId) {
        offerRepository.deleteById(offerId);
    }

    @Override
    public List<OfferDTO> getAllOffers() {
        return offerRepository
                .findAll()
                .stream()
                .map(OfferServiceImpl::map)
                .toList();
    }

    private static OfferDTO map(OfferEntity offerEntity){
        return new OfferDTO(
                offerEntity.getId(),
                offerEntity.getDescription(),
                offerEntity.getPortOfLoading(),
                offerEntity.getPortOfDischarge(),
                offerEntity.getEngine(),
                offerEntity.getPrice());
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
