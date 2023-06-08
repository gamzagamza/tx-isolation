package io.dori.txisolation.service

import io.dori.txisolation.datastore.jpa.CardEntity
import io.dori.txisolation.datastore.jpa.CardRepository
import io.dori.txisolation.shared.CardCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation.*
import org.springframework.transaction.annotation.Transactional

@Service
class CardCreateService(
    private val cardRepository: CardRepository
) {
    @Transactional(propagation = REQUIRES_NEW, isolation = Isolation.REPEATABLE_READ)
    fun create(cardCreateRequest: CardCreateRequest) {
        validate(cardCreateRequest.cardUser)

        val cardEntity = CardEntity(cardUser = cardCreateRequest.cardUser)
        cardRepository.save(cardEntity)
    }

    private fun validate(cardUser: String) {
        val cardEntity = cardRepository.findByCardUser(cardUser)

        if (cardEntity != null) {
            throw IllegalArgumentException("이미 존재하는 카드입니다.")
        }
    }
}
