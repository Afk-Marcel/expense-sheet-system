package expense.sheet.system

import groovy.transform.CompileStatic
import grails.gorm.transactions.Transactional

@CompileStatic
@Transactional
class TransactionService {

        def createTransaction(User user, BigDecimal amount, String description) {
        def transaction = new Transaction()

        transaction.user = user
        transaction.amount = amount
        transaction.description = description

        def newBalance = user.currentBalance - amount
        transaction.runningBalance = newBalance
        user.currentBalance = newBalance

        if(user.save() && transaction.save(flush: true)) {
            return transaction
        } else {
            return null
        }
    }
}