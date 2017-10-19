package ua.com.gup.mongo.model.offer;


import com.fasterxml.jackson.annotation.JsonFormat;
import ua.com.gup.mongo.model.enumeration.OfferRefusalReason;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.List;


public class OfferModerationReport implements Serializable {

    private String moderatorId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    private ZonedDateTime lastModifiedDate;

    private List<OfferRefusalReason> refusalReasons;

    private String description;

    public String getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(String moderatorId) {
        this.moderatorId = moderatorId;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public List<OfferRefusalReason> getRefusalReasons() {
        return refusalReasons;
    }

    public void setRefusalReasons(List<OfferRefusalReason> refusalReasons) {
        this.refusalReasons = refusalReasons;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isRefused() {
        return refusalReasons != null && refusalReasons.size() > 0;
    }
}