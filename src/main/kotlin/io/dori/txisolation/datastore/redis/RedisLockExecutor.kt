package io.dori.txisolation.datastore.redis

import org.redisson.api.RedissonClient
import org.springframework.stereotype.Repository
import java.util.Objects
import java.util.concurrent.TimeUnit

@Repository
class RedisLockExecutor(
    private val redissonClient: RedissonClient
) {
    fun <T> executeLock(lockName: String, waitTime: Long, leaseTime: Long, block: () -> T): T {
        val lock = redissonClient.getLock(lockName)

        if (!lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS)) {
            throw RuntimeException("Lock timeout. lockName=$lockName")
        }

        return try {
            block()
        } catch (e: Exception) {
            throw RuntimeException("Redis lock error. lockName=$lockName, message=${e.message}", e)
        } finally {
            if (Objects.nonNull(lock) && lock.isLocked) {
                lock.unlock()
            }
        }

    }
}
