package ua.com.gup.rent.service.dto.rent.offer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public abstract class RentOfferDTO implements Serializable {

    @ApiModelProperty(position = 10, example = "title")
    private String title;

    @ApiModelProperty(position = 20, example = "description")
    private String description;

}
