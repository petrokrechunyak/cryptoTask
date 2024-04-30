package org.alphabetas.cryptoprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CryptoPriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoPriceApplication.class, args);
    }

}
