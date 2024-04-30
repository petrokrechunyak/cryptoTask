package org.alphabetas.cryptoprice.service;

import org.alphabetas.cryptoprice.model.MarkPriceResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class PriceServiceImpl implements PriceService {

    private final RestTemplate restTemplate;
    private final Map<String, Double> priceCache = new ConcurrentHashMap<>();

    public PriceServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    @Scheduled(fixedRate = 5000)
    public void fetchPrices() {
        String[] pairs = {"BTCUSDT", "ETHUSDT"};


        for (String pair : pairs) {
            String url = String.format("https://fapi.binance.com/fapi/v1/premiumIndex?symbol=%s", pair);
            try {
                MarkPriceResponse response = restTemplate.getForObject(url, MarkPriceResponse.class);
                if (response != null) {
                    priceCache.put(pair, response.getMarkPrice());
                }
                } catch(Exception e){
                    System.err.println("Error fetching price for pair " + pair + ": " + e.getMessage());
                }
        }
    }

    @Override
    public Double getPrice (String pair){
        return priceCache.get(pair);
    }

}





