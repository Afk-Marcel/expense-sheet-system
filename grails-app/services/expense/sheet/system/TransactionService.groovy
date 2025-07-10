package expense.sheet.system

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@CompileStatic
@Transactional

class TransactionService {

    // Injected service for USD conversion
    CurrencyService currencyService

    // Creates a new transaction and updates user balance
    def createTransaction(User user, BigDecimal amount, String description) {
        def transaction = new Transaction()
        transaction.user = user
        transaction.amount = amount
        transaction.description = description

        // Calculate new balance after transaction
        def newBalance = user.currentBalance - amount
        transaction.runningBalance = newBalance
        user.currentBalance = newBalance

        // Calculate USD equivalent
        transaction.usdAmount = currencyService.convertZarToUsd(amount)

        // Save both user and transaction
        if(user.save() && transaction.save(flush: true)) {
            return transaction
        } else {
            return null
        }
    }
}