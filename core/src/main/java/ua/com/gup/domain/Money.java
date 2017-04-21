package ua.com.gup.domain;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;
import ua.com.gup.domain.enumeration.Currency;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Money {

    @LastModifiedDate
    @Field("last_modified_date")
    private LocalDateTime lastModifiedDate = LocalDateTime.now();

    private BigDecimal amount;

    private Currency currency;

    private long baseAmount;

    private Currency baseCurrency;


    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public long getBaseAmount() {
        return baseAmount;
    }

    public void setBaseAmount(long baseAmount) {
        this.baseAmount = baseAmount;
    }

    public Currency getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(Currency baseCurrency) {
        this.baseCurrency = baseCurrency;
    }
}
