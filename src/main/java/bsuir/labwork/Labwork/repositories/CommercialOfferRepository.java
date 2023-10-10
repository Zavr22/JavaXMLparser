package bsuir.labwork.Labwork.repositories;

import bsuir.labwork.Labwork.models.CommercialOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommercialOfferRepository extends JpaRepository<CommercialOffer, Integer> {
}
