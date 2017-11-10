package ua.com.gup.rent.model;

import java.math.BigDecimal;

public class Price {

    private BigDecimal businessDayCost;
    private BigDecimal weekendDayCost;
    private BigDecimal holidayDayCost;

    public Price() {
    }

    public BigDecimal getBusinessDayCost() {
        return businessDayCost;
    }

    public void setBusinessDayCost(BigDecimal businessDayCost) {
        this.businessDayCost = businessDayCost;
    }

    public BigDecimal getWeekendDayCost() {
        return weekendDayCost;
    }

    public void setWeekendDayCost(BigDecimal weekendDayCost) {
        this.weekendDayCost = weekendDayCost;
    }

    public BigDecimal getHolidayDayCost() {
        return holidayDayCost;
    }

    public void setHolidayDayCost(BigDecimal holidayDayCost) {
        this.holidayDayCost = holidayDayCost;
    }
}
