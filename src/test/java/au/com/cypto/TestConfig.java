package au.com.cypto;

import au.com.crypto.repository.CurrencyRepository;
import au.com.crypto.service.CurrencyService;
import au.com.crypto.service.PostProcessingCallback;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;



@Configuration
public class TestConfig{
    @Bean
    public CurrencyService currencyService() {

        return Mockito.mock(CurrencyService.class);
    }

    @Bean
    public PostProcessingCallback postProcessingCallback() {
        // another mock
        return new PostProcessingCallback();
    }

    @Bean
    public CurrencyRepository currencyRepository(){

        return Mockito.mock(CurrencyRepository.class);
    }
}
