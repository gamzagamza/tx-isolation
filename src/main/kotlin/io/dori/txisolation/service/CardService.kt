package io.dori.txisolation.service

import io.dori.txisolation.datastore.redis.RedisLockExecutor
import io.dori.txisolation.shared.CardCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Transactional

@Service
class CardService(
    private val cardCreateService: CardCreateService,
    private val redisLockExecutor: RedisLockExecutor,
) {
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    fun createCard(cardCreateRequest: CardCreateRequest) {
        val lockName = "CARD_LOCK:${cardCreateRequest.cardUser}"

        redisLockExecutor.executeLock(lockName, 500L, 1000L) {
            cardCreateService.create(cardCreateRequest)
        }
    }
}
