package com.hcd.pgsetting;

import com.hcd.pgsetting.domain.Currency;
import com.hcd.pgsetting.domain.CurrencyConversion;
import com.hcd.pgsetting.domain.Product;
import com.hcd.pgsetting.domain.ProductView;
import com.hcd.pgsetting.repository.CurrencyConversionRepository;
import com.hcd.pgsetting.repository.CurrencyRepository;
import com.hcd.pgsetting.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ProductTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CurrencyConversionRepository rateRepository;

    @Autowired
    private ProductService productService;

    private Currency ron, eur;
    private Product watch, painting;
    private CurrencyConversion eurToRon, ronToEur;
    private LocalDate date;

    @BeforeEach
    public void setup() {
        ron = new Currency(1L, "RON");
        eur = new Currency(2L, "EUR");
        currencyRepository.saveAll(List.of(ron, eur));

        watch = new Product(1L, "Swatch Moonlight v1", 100.0d, eur);
        painting = new Product(2L, "Winter Sky", 1000.0d, ron);
        productRepository.saveAll(List.of(watch, painting));

        date = LocalDate.now();
        eurToRon = new CurrencyConversion(1L, date, eur, ron, 5.0d);
        CurrencyConversion eurToEur = new CurrencyConversion(2L, date, eur, eur, 1.0d);
        ronToEur = new CurrencyConversion(3L, date, ron, eur, .2d);
        CurrencyConversion ronToRon = new CurrencyConversion(4L, date, ron, ron, 1.0d);
        rateRepository.saveAll(List.of(eurToRon, eurToEur, ronToEur, ronToRon));
    }

    @Test
    void prices_in_eur() {
        List<ProductView> products = productService.getProducts(eur, date);
        Assertions.assertEquals(2, products.size());

        Assertions.assertTrue(products.stream()
                .allMatch(product -> product.getCurrency().getId().equals(eur.getId())));

        Assertions.assertTrue(products.stream()
                .allMatch(product -> product.getDate().equals(date)));

        Assertions.assertEquals(watch.getPrice(), products.get(0).getPrice());
        Assertions.assertEquals(painting.getPrice() * ronToEur.getValue(), products.get(1).getPrice());
    }

    @Test
    void prices_in_ron() {
        List<ProductView> products = productService.getProducts(ron, date);
        Assertions.assertEquals(2, products.size());

        Assertions.assertTrue(products.stream()
                .allMatch(product -> product.getCurrency().getId().equals(ron.getId())));

        Assertions.assertTrue(products.stream()
                .allMatch(product -> product.getDate().equals(date)));

        Assertions.assertEquals(watch.getPrice() * eurToRon.getValue(), products.get(0).getPrice());
        Assertions.assertEquals(painting.getPrice(), products.get(1).getPrice());
    }
}
