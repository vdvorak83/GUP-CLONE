package ua.com.gup.repository.offer;


import org.springframework.data.domain.Pageable;
import ua.com.gup.model.xchangerate.util.Currency;
import ua.com.gup.mongo.composition.domain.offer.Offer;
import ua.com.gup.mongo.model.enumeration.OfferStatus;
import ua.com.gup.mongo.model.filter.OfferFilter;

import java.util.Collection;
import java.util.List;

public interface OfferRepositoryCustom {

    long countByFilter(OfferFilter offerFilter, OfferStatus offerStatus);

    List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, OfferStatus offerStatus, String excludedId, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Pageable pageable);

    List<Offer> findByFilter(OfferFilter offerFilter, List<OfferStatus> offerStatuses, Collection<String> excludedIds, Pageable pageable);

    void updateBasePriceByExchangeRate(OfferStatus status, Currency currency, Currency baseCurrency, double exchangeRate);

}


