package cat.itacademy.s05.t02.n01.S05T02N01.config;

import cat.itacademy.s05.t02.n01.S05T02N01.services.models.validations.UserValidation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationsConfig {
    
    @Bean
    public UserValidation userValidation () {
        return new UserValidation();
    }
}
