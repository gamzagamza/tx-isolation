package io.dori.txisolation.datastore

import org.springframework.stereotype.Repository
import org.springframework.transaction.support.TransactionOperations

@Repository
class TransactionExecutor(
    private val writeTransactionOperations: TransactionOperations,
    private val readTransactionOperations: TransactionOperations,
) {
    fun <T> doInTransaction(block: () -> T): T {
        return writeTransactionOperations.execute { block() } as T
    }

    fun <T> doInReadTransaction(block: () -> T): T {
        return readTransactionOperations.execute { block() } as T
    }
}
