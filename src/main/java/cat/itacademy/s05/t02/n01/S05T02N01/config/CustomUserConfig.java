package cat.itacademy.s05.t02.n01.S05T02N01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class CustomUserConfig {
    
    @Bean
    public UserDetailsService userDetailsService() {
        
        return new InMemoryUserDetailsManager();
    }
}
