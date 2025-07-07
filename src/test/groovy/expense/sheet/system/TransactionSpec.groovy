package expense.sheet.system

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TransactionSpec extends Specification implements DomainUnitTest<Transaction> {

     void "test domain constraints"() {
        when:
        Transaction domain = new Transaction()
        //TODO: Set domain props here

        then:
        domain.validate()
     }
}
