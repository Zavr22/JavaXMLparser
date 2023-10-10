package bsuir.labwork.Labwork.controllers;

import bsuir.labwork.Labwork.models.CommercialOffer;
import bsuir.labwork.Labwork.services.CommercialOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommercialOfferController {
    private final CommercialOfferService commercialOfferService;

    @Autowired
    public CommercialOfferController(CommercialOfferService commercialOfferService) {
        this.commercialOfferService = commercialOfferService;
    }

    @GetMapping("/commercial_offers")
    public String getOffers(Model model) {
        model.addAttribute("commercial_offers", commercialOfferService.getAllCommercialOffers());
        return "commercial_offers";
    }

    @PostMapping("/add_offer")
    public String addOffer(CommercialOffer commercialOffer) {
        commercialOfferService.saveCommercialOffer(commercialOffer);
        return "redirect:/commercial_offers";
    }

    @GetMapping("/updateOffer/{id}")
    public String updateOfferForm(@PathVariable("id") int id, Model model) {
        CommercialOffer commercialOffer = commercialOfferService.getOfferById(id);
        model.addAttribute("commercial_offer", commercialOffer);
        return "updateOffer";
    }

    @PostMapping("/updateOffer")
    public String updateOffer(CommercialOffer commercialOffer) {
        commercialOfferService.updateCommercialOffer(commercialOffer);
        return "redirect:/commercial_offers";
    }

    @GetMapping("/deleteOffer/{id}")
    public String deleteOffer(@PathVariable int id) {
        commercialOfferService.deleteCommercialOffer(id);
        return "redirect:/commercial_offers";
    }
}
