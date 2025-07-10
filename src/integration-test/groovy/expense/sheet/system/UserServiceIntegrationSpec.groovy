package expense.sheet.system

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification

@Integration
@Rollback
class UserServiceIntegrationSpec extends Specification {

    UserService userService

    def "test createUser sets balances correctly"() {
        given: "user details"
        String name = "John"
        BigDecimal startingBalance = 1000.00

        when: "creating a user"
        def user = userService.createUser(name, startingBalance)

        then: "user should have correct balances"
        user.name == "John"
        user.startingBalance == 1000.00
        user.currentBalance == 1000.00
        user.id != null
    }
}