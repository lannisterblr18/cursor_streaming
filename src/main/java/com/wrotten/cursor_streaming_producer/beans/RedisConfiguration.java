package com.wrotten.cursor_streaming_producer.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Value("${redis.host}")
    private String redisHost;

    @Value("${redis.port}")
    private int redisPort;

    @Value("${redis.username}")
    private String redisUsername;

    @Value("${redis.password}")
    private String redisPassword;



//    @Bean
//    StatefulRedisConnection<String, String> connection() {
//        RedisClient redisClient = RedisClient.create("redis://localhost:6379"); // change to reflect your environment
//        StatefulRedisConnection<String, String> connection = redisClient.connect();
//        return connection;
//    }

////    @Bean
//    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.set
//        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
//                .usePooling().poolConfig(poolConfig)
//                .and().build();
//        return new JedisConnectionFactory(new RedisStandaloneConfiguration());
//
//    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Adjust pool configuration as needed, e.g., maxTotal, maxIdle, etc.
//        JedisClientConfiguration jedisClientConfiguration = JedisClientConfiguration.builder()
//                .usePooling().poolConfig(poolConfig)
//                .and().build();

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setUsername(redisUsername);
        configuration.setHostName(redisHost);
        configuration.setPort(redisPort);
        configuration.setPassword(redisPassword);
        return new JedisConnectionFactory(configuration);
    }
    @Bean
    public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory jedisConnectionFactory) {
        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new GenericJackson2JsonRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        return template;
    }
}
