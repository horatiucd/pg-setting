package com.hcd.pgsetting.repository;

import com.hcd.pgsetting.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {
}
