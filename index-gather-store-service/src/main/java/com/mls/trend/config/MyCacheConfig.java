package com.mls.trend.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
@ConfigurationProperties(prefix="spring.cache.redis")
public class MyCacheConfig {
    private Duration timeToLive=Duration.ZERO;
    public void setTimeToLive(Duration timeToLive){
        this.timeToLive=timeToLive;
    }

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory){
        RedisSerializer<String> redisSerializer=new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer=new Jackson2JsonRedisSerializer(Object.class);

//        解决查询缓存替换异常的问题
        ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.PUBLIC_ONLY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

//        自定义序列化器
        RedisCacheConfiguration config=RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(timeToLive)
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();

        RedisCacheManager cacheManager=RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;


    }
}
