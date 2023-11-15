package com.hcd.pgsetting.repository;

import com.hcd.pgsetting.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {
}
