package io.dori.txisolation.controller

import io.dori.txisolation.service.CardService
import io.dori.txisolation.shared.CardCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CardController(
    private val cardService: CardService
) {

    @PostMapping("/api/v1/card/cards")
    fun createCard(@RequestBody cardCreateRequest: CardCreateRequest): ResponseEntity<String> {
        cardService.createCard(cardCreateRequest)
        return ResponseEntity.ok("SUCCESS")
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ResponseEntity<String> {
        return ResponseEntity.badRequest().body(e.message)
    }
}
