package io.dori.txisolation.datastore.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository: JpaRepository<CardEntity, Long> {
    fun findByCardUser(cardUser: String): CardEntity?
}
