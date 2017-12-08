package ua.com.gup.rent.filter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "RentOfferRange model")
public class RentOfferRange<T> {

    @ApiModelProperty("Value from inclusive")
    private T from;

    @ApiModelProperty("Value to inclusive")
    private T to;

    public T getFrom() {
        return from;
    }

    public void setFrom(T from) {
        this.from = from;
    }

    public T getTo() {
        return to;
    }

    public void setTo(T to) {
        this.to = to;
    }
}