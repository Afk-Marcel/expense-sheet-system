package expense.sheet.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification

@Integration
@Rollback
class TransactionServiceIntegrationSpec extends Specification {

    TransactionService transactionService

    def "test createTransaction reduces user balance"() {
        given: "a saved user"
        def user = new User(name: "Test", startingBalance: 1000, currentBalance: 1000)
        user.save(flush: true, failOnError: true)

        when: "creating a transaction"
        def transaction = transactionService.createTransaction(user, 100 as BigDecimal, "Test purchase")

        then: "user balance should be reduced and transaction created"
        user.currentBalance == 900
        transaction.amount == 100
        transaction.description == "Test purchase"
        transaction.usdAmount > 0
    }
}