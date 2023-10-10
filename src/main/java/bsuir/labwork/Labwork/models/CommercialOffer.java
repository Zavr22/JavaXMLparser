package bsuir.labwork.Labwork.models;

import bsuir.labwork.Labwork.exceptions.InvalidCommercialOfferException;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Entity
@Table(name = "commercial_offers")
public class CommercialOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private static final String COMMERCIAL_OFFER_NAME_PATTERN= "[A-Za-z\\s\\d]+";
    private static final String COMMERCIAL_OFFER_PRODUCT_NAME_PATTERN = "[A-Za-z\\s\\d]+";
    private static final String COMMERCIAL_OFFER_PRODUCT_PRICE_PATTERN = "\\d+";
    private static final String COMMERCIAL_OFFER_PRICE_PATTERN = "\\d+";

    private String commercialOfferName;
    private String commercialOfferPrice;
    private List<Product> products;


    public CommercialOffer(String commercialOfferName, String commercialOfferPrice, List<Product> products) {
        this.commercialOfferName = commercialOfferName;
        this.commercialOfferPrice = commercialOfferPrice;
        this.products = products;
    }

    public CommercialOffer() {

        this.products = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "CommercialOffer{" +
                "commercialOfferName='" + commercialOfferName + '\'' +
                ", commercialOfferPrice='" + commercialOfferPrice + '\'' +
                ", products=" + products +
                '}';
    }

    public void setCommercialOfferName(String commercialOfferName) {
        this.commercialOfferName = commercialOfferName;
    }

    public void setCommercialOfferPrice(String commercialOfferPrice) {
        this.commercialOfferPrice = commercialOfferPrice;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void validate() throws InvalidCommercialOfferException {
        if (!Pattern.matches(COMMERCIAL_OFFER_NAME_PATTERN, commercialOfferName)) {
            throw new InvalidCommercialOfferException("Invalid commercial offer name");
        }
        for(Product p:this.getProducts()) {
            if (!Pattern.matches(COMMERCIAL_OFFER_PRODUCT_NAME_PATTERN, p.getName())) {
                throw new InvalidCommercialOfferException("Invalid CardHolder");
            }
            if (!Pattern.matches(COMMERCIAL_OFFER_PRODUCT_PRICE_PATTERN, p.getPrice())){
                throw new InvalidCommercialOfferException("Invalid product price");
            }
        }
        if (!Pattern.matches(COMMERCIAL_OFFER_PRICE_PATTERN, commercialOfferPrice)) {
            throw new InvalidCommercialOfferException("Invalid commercial offer price");
        }
    }

}