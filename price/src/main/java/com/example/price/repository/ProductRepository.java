package com.example.price.repository;

import com.example.price.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findByArticleIdAndStoreStoreId(String articleId, String storeId);
}
