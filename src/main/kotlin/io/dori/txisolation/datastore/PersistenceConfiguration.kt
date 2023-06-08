package io.dori.txisolation.datastore

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.TransactionDefinition
import org.springframework.transaction.support.TransactionTemplate

@Configuration
class PersistenceConfiguration(
    private val transactionManager: PlatformTransactionManager
) {

    @Bean
    fun writeTransactionOperations(): TransactionTemplate {
        val transactionTemplate = TransactionTemplate(transactionManager)
        transactionTemplate.isReadOnly = false
        transactionTemplate.isolationLevel = TransactionDefinition.ISOLATION_REPEATABLE_READ
        return transactionTemplate
    }

    @Bean
    fun readTransactionOperations(): TransactionTemplate {
        val transactionTemplate = TransactionTemplate(transactionManager)
        transactionTemplate.isReadOnly = true
        transactionTemplate.isolationLevel = TransactionDefinition.ISOLATION_REPEATABLE_READ
        return transactionTemplate
    }
}
