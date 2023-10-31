package com.hcd.pgsetting.repository;

import com.hcd.pgsetting.domain.CurrencyConversion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyConversionRepository extends CrudRepository<CurrencyConversion, Long> {
}
