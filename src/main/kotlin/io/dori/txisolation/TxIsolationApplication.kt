package io.dori.txisolation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TxIsolationApplication

fun main(args: Array<String>) {
    runApplication<TxIsolationApplication>(*args)
}
