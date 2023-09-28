package com.udemy.springbootdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String REDIS_HOSTNAME;

    @Value("${spring.redis.port}")
    private int REDIS_PORT;

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setHashKeySerializer(new GenericToStringSerializer<String>(String.class));

        redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOSTNAME, REDIS_PORT);

        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder().build();

        JedisConnectionFactory factory = new JedisConnectionFactory(configuration, jedisClientConfiguration);

        factory.afterPropertiesSet();
        redisTemplate.setConnectionFactory(factory);
        return redisTemplate;
    }

}
