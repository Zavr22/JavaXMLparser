package bsuir.labwork.Labwork.services;


import bsuir.labwork.Labwork.models.CommercialOffer;
import bsuir.labwork.Labwork.repositories.CommercialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommercialOfferService {
    private final CommercialOfferRepository repository;

    @Autowired
    public CommercialOfferService(CommercialOfferRepository repository) {
        this.repository = repository;
    }

    public List<CommercialOffer> getAllCommercialOffers() {
        return repository.findAll();
    }

    public void saveCommercialOffer(CommercialOffer commercialOffer) {
        repository.save(commercialOffer);
    }

    public void updateCommercialOffer(CommercialOffer commercialOffer) {
        repository.save(commercialOffer);
    }

    public void deleteCommercialOffer(int id) {
        repository.deleteById(id);
    }

    public CommercialOffer getOfferById(int id) {
        return repository.findById(id).orElse(null);
    }
}
