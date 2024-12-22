package com.example.price.dto;

import com.example.price.entity.Price;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricingResponse {
    private String generatedDate;
    private String article;
    private String store;
    private MetaData meta;
    private ProductProperties properties;
    private List<PriceDTO> priceList;

    public String getGeneratedDate() {
        return generatedDate;
    }

    public void setGeneratedDate(String generatedDate) {
        this.generatedDate = generatedDate;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public MetaData getMeta() {
        return meta;
    }

    public void setMeta(MetaData meta) {
        this.meta = meta;
    }

    public ProductProperties getProperties() {
        return properties;
    }

    public void setProperties(ProductProperties properties) {
        this.properties = properties;
    }

    public List<PriceDTO> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<PriceDTO> priceList) {
        this.priceList = priceList;
    }
}
