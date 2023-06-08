package io.dori.txisolation.task

import io.dori.txisolation.shared.CardCreateRequest
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate
import java.net.URI

class MultipleRequest

fun main() {
    val restTemplate = RestTemplate()

    for (i in 1..10) {
        Thread {
            try {
                val response = restTemplate.exchange(
                    URI("http://localhost:8080/api/v1/card/cards"),
                    HttpMethod.POST,
                    HttpEntity(CardCreateRequest("user")),
                    String::class.java
                )

                Thread.sleep(1000L)
                println("${i}번째 요청 >>> ${response.body}")
            } catch (e: Exception) {
                Thread.sleep(1000L)
                println("${i}번째 요청 >>> ERROR: ${e.message}")
            }
        }.start()
    }
}
