package ua.com.gup.service.dto;

import io.swagger.annotations.ApiModelProperty;
import ua.com.gup.domain.OfferCategory;

import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class OfferShortDTO extends OfferBaseDTO {

    @ApiModelProperty(position = 0, example = "58ff0d6c821847a4bc8c5bff")
    private String id;

    @ApiModelProperty(position = 5)
    private ZonedDateTime lastModifiedDate;

    @ApiModelProperty(position = 20, example = "58edf17a4c8e83648c2f1aa3")
    private String authorId;

    @ApiModelProperty(position = 30)
    private LinkedList<OfferCategory> categories;

    @ApiModelProperty(position = 60)
    private OfferAddressShortDTO address;

    @ApiModelProperty(position = 80)
    private LinkedHashSet<String> imageIds;

    @ApiModelProperty(position = 90, example = "prodam-toyota-rav-4-2016hod-ls")
    private String seoUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public LinkedList<OfferCategory> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<OfferCategory> categories) {
        this.categories = categories;
    }

    public OfferAddressShortDTO getAddress() {
        return address;
    }

    public void setAddress(OfferAddressShortDTO address) {
        this.address = address;
    }

    public LinkedHashSet<String> getImageIds() {
        return imageIds;
    }

    public void setImageIds(LinkedHashSet<String> imageIds) {
        this.imageIds = imageIds;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }
}