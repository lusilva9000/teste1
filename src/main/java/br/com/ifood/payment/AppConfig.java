package br.com.ifood.payment;

import br.com.ifood.payment.models.Coupon;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class AppConfig {

    @Bean
    public RedisTemplate<String, Coupon> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Coupon> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
