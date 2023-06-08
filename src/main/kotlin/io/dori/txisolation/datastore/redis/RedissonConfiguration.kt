package io.dori.txisolation.datastore.redis

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfiguration(
    @Value("\${redis.host}")
    private val redisHost: String,

    @Value("\${redis.port}")
    private val redisPort: Int,
) {
    @Bean
    fun redissonClient(): RedissonClient {
        val config = Config()
        config.useSingleServer().address = "redis://$redisHost:$redisPort"
        return Redisson.create(config)
    }
}
