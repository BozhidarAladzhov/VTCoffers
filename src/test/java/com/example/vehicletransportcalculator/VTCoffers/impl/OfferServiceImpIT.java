package com.example.vehicletransportcalculator.VTCoffers.impl;

import com.example.vehicletransportcalculator.VTCoffers.model.dto.AddOfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.dto.OfferDTO;
import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.EngineTypeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.example.vehicletransportcalculator.VTCoffers.service.OceanFreightService;
import com.example.vehicletransportcalculator.VTCoffers.service.impl.OfferServiceImpl;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class OfferServiceImpIT {

    @Autowired
    private OfferServiceImpl offerService;

    @Autowired
    private OfferRepository offerRepository;

    @MockBean
    private OceanFreightService oceanFreightService;

    @Test
    void createOffer() {
        // Arrange
        AddOfferDTO dto = new AddOfferDTO(
                "Test description",
                PortOfLoadingEnum.NEW_YORK,
                PortOfDischargeEnum.ROTTERDAM,
                EngineTypeEnum.PETROL,
                2000
        );

        BigDecimal mockedFreight = BigDecimal.valueOf(1100);
        when(oceanFreightService.calculateOceanFreight(dto.portOfLoading(), dto.portOfDischarge()))
                .thenReturn(mockedFreight);

        // Act
        OfferDTO created = offerService.createOffer(dto);

        // Assert
        assertThat(created).isNotNull();
        assertThat(created.description()).isEqualTo("Test description");
        assertThat(created.portOfLoading()).isEqualTo(PortOfLoadingEnum.NEW_YORK);
        assertThat(created.portOfDischarge()).isEqualTo(PortOfDischargeEnum.ROTTERDAM);
        assertThat(created.engineType()).isEqualTo(EngineTypeEnum.PETROL);
        assertThat(created.price()).isEqualTo(2000);
        assertThat(created.oceanFreight()).isEqualTo(mockedFreight);

        // Check it's persisted
        var savedEntity = offerRepository.findById(created.id());
        assertThat(savedEntity).isPresent();
        assertThat(savedEntity.get().getDescription()).isEqualTo("Test description");

        // Check service call
        verify(oceanFreightService, times(1))
                .calculateOceanFreight(dto.portOfLoading(), dto.portOfDischarge());
    }

    @Test
    void testGetOfferById_returnsCorrectOffer() {
        // Arrange
        OfferEntity saved = offerRepository.save(
                new OfferEntity()
                        .setDescription("Lookup Offer")
                        .setPortOfLoading(PortOfLoadingEnum.NEW_YORK)
                        .setPortOfDischarge(PortOfDischargeEnum.ROTTERDAM)
                        .setEngine(EngineTypeEnum.DIESEL)
                        .setPrice(1500)
        );

        when(oceanFreightService.calculateOceanFreight(saved.getPortOfLoading(), saved.getPortOfDischarge()))
                .thenReturn(BigDecimal.valueOf(900));

        // Act
        OfferDTO result = offerService.getOfferById(saved.getId());

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(saved.getId());
        assertThat(result.description()).isEqualTo("Lookup Offer");
        assertThat(result.portOfDischarge()).isEqualTo(PortOfDischargeEnum.ROTTERDAM);
        assertThat(result.oceanFreight()).isEqualTo(BigDecimal.valueOf(900));
    }

    @Test
    void testDeleteOffer_removesOffer() {
        // Arrange
        OfferEntity offer = offerRepository.save(
                new OfferEntity()
                        .setDescription("To Be Deleted")
                        .setPortOfLoading(PortOfLoadingEnum.NEW_YORK)
                        .setPortOfDischarge(PortOfDischargeEnum.ROTTERDAM)
                        .setEngine(EngineTypeEnum.ELECTRIC)
                        .setPrice(2500)
        );

        UserDetails mockUser = mock(UserDetails.class);

        // Act
        offerService.deleteOffer(mockUser, offer.getId());

        // Assert
        assertThat(offerRepository.findById(offer.getId())).isEmpty();
    }

    @Test
    void testGetAllOffers_returnsList() {
        // Arrange
        offerRepository.deleteAll(); // Clean up to avoid conflicts

        OfferEntity offer1 = offerRepository.save(
                new OfferEntity()
                        .setDescription("Offer One")
                        .setPortOfLoading(PortOfLoadingEnum.NEW_YORK)
                        .setPortOfDischarge(PortOfDischargeEnum.ROTTERDAM)
                        .setEngine(EngineTypeEnum.HYBRID)
                        .setPrice(1000)
        );

        OfferEntity offer2 = offerRepository.save(
                new OfferEntity()
                        .setDescription("Offer Two")
                        .setPortOfLoading(PortOfLoadingEnum.SAN_FRANCISCO)
                        .setPortOfDischarge(PortOfDischargeEnum.VARNA)
                        .setEngine(EngineTypeEnum.DIESEL)
                        .setPrice(1800)
        );

        when(oceanFreightService.calculateOceanFreight(any(), any()))
                .thenReturn(BigDecimal.valueOf(1000));

        // Act
        List<OfferDTO> offers = offerService.getAllOffers();

        // Assert
        assertEquals("Offer One", offer1.getDescription());
        assertEquals("Offer Two", offer2.getDescription());
        assertEquals(2, offers.size());
    }

}
