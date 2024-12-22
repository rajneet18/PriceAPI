package com.example.price.controller;

import com.example.price.dto.PricingResponse;
import com.example.price.service.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pricing/v1")
public class PricingController {
    @Autowired
    private PricingService pricingService;

    @GetMapping("/prices/{storeID}/{articleID}")
    public PricingResponse getPrices(@PathVariable String storeID, @PathVariable String articleID, @RequestParam int page, @RequestParam int pageSize) {
        return pricingService.getPricingInfo(storeID, articleID, page, pageSize);
    }
}
