package com.kajs.config;

import com.kajs.domain.CalculateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public CalculateService calculateService(){
        return new CalculateService();
    }
}
