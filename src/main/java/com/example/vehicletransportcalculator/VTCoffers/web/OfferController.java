package com.example.vehicletransportcalculator.VTCoffers.web;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;

import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;
import com.example.vehicletransportcalculator.VTCoffers.service.OceanFreightService;
import com.example.vehicletransportcalculator.VTCoffers.service.OfferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/offers") // TODO IS IT THE RIGHT REQUEST MAPPING PATH ???, CHECK WITH VTC project.
public class OfferController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDTO> getById (@PathVariable("id")Long id){
        return ResponseEntity
                .ok(offerService.getOfferById(id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<OfferDTO> deleteById (@PathVariable("id")Long id){
        offerService.deleteOffer(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OfferDTO>> getAllOffers(){
<<<<<<< HEAD
        return ResponseEntity
                .ok(offerService.getAllOffers());
=======
            return ResponseEntity
                    .ok(offerService.getAllOffers());
    }
>>>>>>> parent of 4025fdc (Delete method)

    @PostMapping
    public ResponseEntity <OfferDTO> createOffer (@RequestBody AddOfferDTO addOfferDTO){
            LOGGER.info("Going to create an offer {}", addOfferDTO);

        OfferDTO offerDTO = offerService.createOffer(addOfferDTO);
            return ResponseEntity.
                    created(ServletUriComponentsBuilder
                            .fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(offerDTO.id())
                            .toUri())
                    .body(offerDTO);

    }





}
