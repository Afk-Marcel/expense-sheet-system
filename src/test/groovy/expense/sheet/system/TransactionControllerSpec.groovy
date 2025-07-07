package expense.sheet.system

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TransactionControllerSpec extends Specification implements ControllerUnitTest<TransactionController> {

     void "test index action"() {
        when:
        controller.index()

        then:
        status == 200

     }
}
