package com.hcd.pgsetting.repository;

import com.hcd.pgsetting.domain.ProductView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductViewRepository extends org.springframework.data.repository.Repository<ProductView, Long> {

    List<ProductView> findAll();

    @Query(value = "SELECT set_config(:name, :value, true)")
    void setConfigParam(String name, String value);
}
