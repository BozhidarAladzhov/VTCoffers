package com.example.vehicletransportcalculator.VTCoffers.repository;

import com.example.vehicletransportcalculator.VTCoffers.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {
}
