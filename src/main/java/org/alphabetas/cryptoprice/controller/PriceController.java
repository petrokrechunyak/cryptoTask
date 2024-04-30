package org.alphabetas.cryptoprice.controller;

import org.alphabetas.cryptoprice.service.PriceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/crypto")
public class PriceController {

    private final PriceService priceService;

    public PriceController(PriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/price/{pair}")
    public double getPrice(@PathVariable String pair) {
        return Optional.ofNullable(priceService.getPrice(pair))
                .orElseThrow(() -> new RuntimeException("Price not found for pair: " + pair));
    }
}

