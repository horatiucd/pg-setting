package com.hcd.pgsetting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDate;

@Entity
@Immutable
public class ProductView {

    public static final String PARAM_CURRENCY_ID = "pgsetting.CurrencyId";
    public static final String PARAM_CURRENCY_DATE = "pgsetting.CurrencyDate";

    @Id
    private Long id;

    private String name;

    private Double price;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    public Currency getCurrency() {
        return currency;
    }
}
