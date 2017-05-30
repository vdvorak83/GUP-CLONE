package ua.com.gup.web.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.gup.domain.complaint.ComplaintOffer;
import ua.com.gup.domain.complaint.ComplaintOfferDescription;
import ua.com.gup.domain.complaint.ComplaintOfferStatus;
import ua.com.gup.service.ComplaintOfferService;
import ua.com.gup.service.security.SecurityUtils;
import ua.com.gup.web.rest.util.HeaderUtil;
import ua.com.itproekt.gup.model.profiles.UserRole;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * REST controller for managing ComplaintOffer.
 */
@RestController
@RequestMapping("/api")
public class ComplaintOfferResource {

    private static final String ENTITY_NAME = "complaint";
    private final Logger log = LoggerFactory.getLogger(ComplaintOfferResource.class);

    @Autowired
    private ComplaintOfferService complaintOfferService;

    /**
     * POST  /complaints : Create a new complaintOffer.
     *
     * @param complaintOffer the complaintOffer to create
     * @return the ResponseEntity with status 201 (Created) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createComplaintOffer(@Valid @RequestBody ComplaintOffer complaintOffer)
            throws URISyntaxException {
        log.debug("REST request to save new ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOffer.setInitiatorId(SecurityUtils.getLoggedUser().getProfileId());
        complaintOfferService.save(complaintOffer);

        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param complaintOffer the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/status", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferStaus(@Valid @RequestBody ComplaintOffer complaintOffer, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOfferService.updateStatus(id, complaintOffer.getStatus());

        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param description the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/description", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferDescription(@RequestBody ComplaintOfferDescription description, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", description);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOfferService.updateDescription(id, description);

        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * PUT  /complaints : Update a staus complaintOffer.
     *
     * @param complaintOffer the complaintOffer to update
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}/descriptions", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateComplaintOfferDescriptions(@RequestBody ComplaintOffer complaintOffer, @PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to update ComplaintOffer : {}", complaintOffer);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        complaintOfferService.updateDescriptions(id, complaintOffer.getDescriptions());

        return new ResponseEntity(HttpStatus.OK);
    }


    /**
     * GET  /complaints : Get a staus complaintOffer.
     *
     * @param id the complaintOffer to gte
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComplaintOffer> getComplaintOfferById(@PathVariable("id") String id)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer : {}", id);
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findOne(id), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get all complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferAll()
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAll(), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a offerId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{offerId}/offerId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByOfferId(@PathVariable("offerId") String offerId)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAllByOfferId(offerId), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a initiatorId complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{initiatorId}/initiatorId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByInitiatorId(@PathVariable("initiatorId") String initiatorId)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAllByInitiatorId(initiatorId), HttpStatus.OK);
    }


    /**
     * GET  /complaints : get a status complaintOffer.
     *
     * @return the ResponseEntity with status 200 (Ok) and with body the new complaintOffer, or with status 400 (Bad Request) if the offer has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @CrossOrigin
    @RequestMapping(value = "/complaints/{status}/status", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComplaintOffer>> getComplaintOfferByStatus(@PathVariable("status") ComplaintOfferStatus status)
            throws URISyntaxException {
        log.debug("REST request to get ComplaintOffer's");
        if (!SecurityUtils.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "unauthorized", "Need authorization")).body(null);
        }
        if (!SecurityUtils.isCurrentUserInRole(UserRole.ROLE_USER)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "forbidden", "User should be in role 'ROLE_USER'")).body(null);
        }

        return new ResponseEntity(complaintOfferService.findAllByStatus(status), HttpStatus.OK);
    }

}