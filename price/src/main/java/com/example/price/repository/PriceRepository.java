package com.example.price.repository;

import com.example.price.entity.Price;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PriceRepository extends JpaRepository<Price, Long> {
    List<Price> findByProductArticleIdAndProductStoreStoreId(String articleId, String storeId, Pageable pageable);
}
