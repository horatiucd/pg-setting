package com.hcd.pgsetting;

import com.hcd.pgsetting.domain.Currency;
import com.hcd.pgsetting.domain.ProductView;
import com.hcd.pgsetting.repository.ProductViewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductService {

    private final ProductViewRepository productViewRepository;

    public ProductService(ProductViewRepository productViewRepository) {
        this.productViewRepository = productViewRepository;
    }

    @Transactional
    public List<ProductView> getProducts(Currency currency, LocalDate date) {
        productViewRepository.setConfigParam(ProductView.PARAM_CURRENCY_ID,
                String.valueOf(currency.getId()));

        productViewRepository.setConfigParam(ProductView.PARAM_CURRENCY_DATE,
                DateTimeFormatter.ofPattern("yyyy-MM-dd").format(date));

        return productViewRepository.findAll();
    }
}
