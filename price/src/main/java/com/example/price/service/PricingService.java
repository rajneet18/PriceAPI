package com.example.price.service;

import com.example.price.dto.MetaData;
import com.example.price.dto.PriceDTO;
import com.example.price.dto.PricingResponse;
import com.example.price.dto.ProductProperties;
import com.example.price.entity.Price;
import com.example.price.entity.Product;
import com.example.price.exception.PriceNotFoundException;
import com.example.price.repository.PriceRepository;
import com.example.price.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PricingService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PriceRepository priceRepository;/*
    @Autowired
    private MetaDataRepository metaDataRepository;*/

    public PricingResponse getPricingInfo(String storeId, String articleId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        // Fetch the product and its associated prices
        Optional<Product> product = productRepository.findByArticleIdAndStoreStoreId(articleId, storeId);
        if (product.isEmpty()) {
            throw new PriceNotFoundException("Product or Store not found");
        }
        PricingResponse response = new PricingResponse();

        response.setGeneratedDate(LocalDateTime.now().toString());
        response.setArticle(articleId);
        response.setStore(storeId);

        MetaData metaData = new MetaData();
        metaData.setPage(page);
        metaData.setSize(pageSize);

        response.setMeta(metaData);

        ProductProperties properties = new ProductProperties();
        properties.setDescription(product.get().getDescription());
        properties.setBrand(product.get().getBrand());
        properties.setModel(product.get().getModel());
        properties.setUom(product.get().getUom());

        response.setProperties(properties);
        response.setPriceList(mergePrices(getPriceData(product.get().getPrices())));

        return response;
    }


    // Function to merge prices with overlapping ranges and same price
    public List<PriceDTO> mergePrices(List<PriceDTO> prices) {
        List<PriceDTO> mergedPrices = new ArrayList<>();

        // Sort prices by the 'validFrom' time
        prices.sort((p1, p2) -> p1.getValidFrom().compareTo(p2.getValidFrom()));

        for (int i = 0; i < prices.size(); i++) {
            PriceDTO current = prices.get(i);

            // Check if we can merge this price with the next one
            for (int j = i + 1; j < prices.size(); j++) {
                PriceDTO next = prices.get(j);

                // If prices overlap (and have the same price)
                if (isOverlap(current, next) && Objects.equals(current.getAmount(), next.getAmount())) {
                    // Merge them into one with a combined validity range
                    current = mergePriceRanges(current, next);
                    next.setOverlapped(true); // Mark the next price as overlapped
                    prices.remove(j); // Remove the merged price
                    j--; // Adjust index as list is modified
                } else if (isOverlap(current, next) && !Objects.equals(current.getAmount(), next.getAmount())) {
                    // Mark the next price as overlapped
                    next.setOverlapped(true);
                }
            }

            if (!mergedPrices.contains(current)) {
                mergedPrices.add(current); // Add the price to the list if not already added
            }
        }
        return mergedPrices;
    }

    // Helper method to check if two prices overlap
    private boolean isOverlap(PriceDTO p1, PriceDTO p2) {
        return !(p1.getValidTo().isBefore(p2.getValidFrom()) || p2.getValidTo().isBefore(p1.getValidFrom()));
    }

    // Helper method to merge two prices with the same amount
    private PriceDTO mergePriceRanges(PriceDTO p1, PriceDTO p2) {
        LocalDateTime mergedFrom = p1.getValidFrom().isBefore(p2.getValidFrom()) ? p1.getValidFrom() : p2.getValidFrom();
        LocalDateTime mergedTo = p1.getValidTo().isAfter(p2.getValidTo()) ? p1.getValidTo() : p2.getValidTo();

        return new PriceDTO(p1.getId(), p1.getType(), p1.getSubtype(), p1.getCurrency(), p1.getAmount(), mergedFrom, mergedTo);
    }

    //Method to set data from product into priceDTO
    public List<PriceDTO> getPriceData(List<Price> prices) {
        return prices.stream()
                .map(price -> new PriceDTO(price.getId(), price.getType(), price.getSubtype(), price.getCurrency(),
                        price.getAmount(), price.getValidFrom(), price.getValidTo()))
                .collect(Collectors.toList());
    }
}
