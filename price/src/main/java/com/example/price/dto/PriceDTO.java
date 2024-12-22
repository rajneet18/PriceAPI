package com.example.price.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PriceDTO  {

    private Long id;

    public boolean isOverlapped() {
        return overlapped;
    }

    public void setOverlapped(boolean overlapped) {
        this.overlapped = overlapped;
    }

    private boolean overlapped;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    private String type;

    public PriceDTO(Long id, String type, String subtype, String currency, Double amount, LocalDateTime validFrom, LocalDateTime validTo) {
        this.id = id;
        this.type = type;
        this.subtype = subtype;
        this.currency = currency;
        this.amount = amount;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    private String subtype;
    private String currency;
    private Double amount;
    private LocalDateTime validFrom;
    private LocalDateTime validTo;

}
