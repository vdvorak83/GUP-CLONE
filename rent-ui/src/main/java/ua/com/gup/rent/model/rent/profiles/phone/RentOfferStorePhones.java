package ua.com.gup.rent.model.rent.profiles.phone;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RentOfferStorePhones {
    private String idUser;
    private List<Long> mainPhones;
    private List<RentOfferPhoneSynhronize> contactPhones;

}

