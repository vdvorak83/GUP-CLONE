package ua.com.itproekt.gup.api.rest.offers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ua.com.itproekt.gup.bank_api.BankSession;
import ua.com.itproekt.gup.model.offer.Offer;
import ua.com.itproekt.gup.model.offer.OfferUserContactInfo;
import ua.com.itproekt.gup.model.offer.Reservation;
import ua.com.itproekt.gup.model.profiles.Profile;
import ua.com.itproekt.gup.service.offers.OffersService;
import ua.com.itproekt.gup.service.profile.ProfilesService;
import ua.com.itproekt.gup.util.SecurityOperations;

@RestController
@RequestMapping("/api/rest/offersService")
public class OfferReservationRestController {

    @Autowired
    OffersService offersService;

    @Autowired
    ProfilesService profilesService;

    @Autowired
    BankSession bankSession;

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/reserve", method = RequestMethod.POST)
    public ResponseEntity<Void> reserveOffer(@PathVariable String offerId) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Offer offer = offersService.findById(offerId);
        if (!offer.getCanBeReserved() || (offer.getReservation() != null)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        String userId = SecurityOperations.getLoggedUserId();

        Profile profile = profilesService.findById(userId);

        Reservation reservation = new Reservation()
                .setProfileId(userId)
                .setUserContactInfo(new OfferUserContactInfo()
                        .setContactName(profile.getUsername())
                        .setEmail(profile.getEmail())
                        .setPhoneNumbers(profile.getContact().getContactPhones())
                        .setSkypeLogin(profile.getContact().getSkypeUserName()));
        offersService.reserveOffer(offerId, reservation);
        bankSession.investInOrganization(5555, userId, 5L, 30, "success");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value = "/offer/id/{offerId}/deleteReservation", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteReservation(@PathVariable String offerId) {
        if (!offersService.offerExists(offerId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String authorId = offersService.findById(offerId).getAuthorId();
        String userId = SecurityOperations.getLoggedUserId();
        if (!userId.equals(authorId)) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        offersService.deleteReservation(offerId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}