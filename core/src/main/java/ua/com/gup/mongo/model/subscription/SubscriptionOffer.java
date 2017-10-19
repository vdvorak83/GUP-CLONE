package ua.com.gup.mongo.model.subscription;


import ua.com.gup.mongo.model.filter.OfferFilter;

public class SubscriptionOffer {

    private OfferFilter offerFilter;
    private String email; // email for not authorized person

    public OfferFilter getOfferFilter() {
        return offerFilter;
    }

    public void setOfferFilterOptions(OfferFilter offerFilter) {
        this.offerFilter = offerFilter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "SubscriptionOffer{" +
                "offerFilter=" + offerFilter +
                ", email='" + email + '\'' +
                '}';
    }

}