package com.hcd.pgsetting.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "currency_conversion")
public class CurrencyConversion {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "from_currency_id", nullable = false)
    private Currency from;

    @ManyToOne
    @JoinColumn(name = "to_currency_id", nullable = false)
    private Currency to;

    @Column(name = "value", nullable = false)
    private Double value;

    public CurrencyConversion() {

    }

    public CurrencyConversion(Long id, LocalDate date, Currency from, Currency to, Double value) {
        this.id = id;
        this.date = date;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Currency getFrom() {
        return from;
    }

    public void setFrom(Currency from) {
        this.from = from;
    }

    public Currency getTo() {
        return to;
    }

    public void setTo(Currency to) {
        this.to = to;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
