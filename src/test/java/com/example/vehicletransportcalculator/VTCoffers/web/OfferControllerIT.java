package com.example.vehicletransportcalculator.VTCoffers.web;

import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.EngineTypeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfDischargeEnum;
import com.example.vehicletransportcalculator.VTCoffers.model.enums.PortOfLoadingEnum;
import com.example.vehicletransportcalculator.VTCoffers.repository.OfferRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(
        username = "pesho@example.com",
        roles = {"USER", "ADMIN"}
)
public class OfferControllerIT {

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    public void tearDown() {
        offerRepository.deleteAll();
    }

    @Test
    public void testGetById() throws Exception {

        // Arrange
        var actualEntity = createTestOffer();

        // ACT
        ResultActions result = mockMvc
                .perform(get("/offers/{id}", actualEntity.getId())
                        .contentType(MediaType.APPLICATION_JSON));

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(actualEntity.getId().intValue())))
                .andExpect(jsonPath("$.description", is(actualEntity.getDescription())))
                .andExpect(jsonPath("$.portOfLoading", is(actualEntity.getPortOfLoading().name())))
                .andExpect(jsonPath("$.portOfDischarge", is(actualEntity.getPortOfDischarge().name())))
                .andExpect(jsonPath("$.engineType", is(actualEntity.getEngine().name())))
                .andExpect(jsonPath("$.price", is(actualEntity.getPrice())));
    }

    @Test
    public void testOfferNotFound() throws Exception {
        mockMvc
                .perform(get("/offers/{id}", "1000000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOffer() throws Exception {
        MvcResult result = mockMvc.perform(post("/offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                  {
                    "description": "Test description",
                    "portOfLoading": "NEW_YORK",
                    "portOfDischarge": "ROTTERDAM",
                    "engineType": "PETROL",
                    "price": 2000
                  }
                """)
                ).andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andReturn();

        String body = result.getResponse().getContentAsString();

        int id = JsonPath.read(body, "$.id");

        Optional<OfferEntity> createdOfferOpt = offerRepository.findById((long) id);

        Assertions.assertTrue(createdOfferOpt.isPresent());

        OfferEntity createdOffer = createdOfferOpt.get();

        Assertions.assertEquals("Test description", createdOffer.getDescription());
        Assertions.assertEquals( PortOfLoadingEnum.NEW_YORK, createdOffer.getPortOfLoading());
        Assertions.assertEquals(PortOfDischargeEnum.ROTTERDAM, createdOffer.getPortOfDischarge());
        Assertions.assertEquals(EngineTypeEnum.PETROL, createdOffer.getEngine());
        Assertions.assertEquals(2000, createdOffer.getPrice());

    }

    @Test
    public void testDeleteOffer() throws Exception {

        var actualEntity = createTestOffer();

        mockMvc.perform(delete("/offers/{id}", actualEntity.getId())
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());

        Assertions.assertTrue(
                offerRepository.findById(actualEntity.getId()).isEmpty()
        );
    }


    private OfferEntity createTestOffer() {
        return offerRepository.save(
                new OfferEntity()
                        .setDescription("Test description")
                        .setPortOfLoading(PortOfLoadingEnum.NEW_YORK)
                        .setPortOfDischarge(PortOfDischargeEnum.ROTTERDAM)
                        .setEngine(EngineTypeEnum.PETROL)
                        .setPrice(2000)
        );
    }
}
