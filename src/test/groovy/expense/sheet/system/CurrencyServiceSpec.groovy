package expense.sheet.system

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class CurrencyServiceSpec extends Specification implements ServiceUnitTest<CurrencyService> {

    void "test convertZarToUsd with basic conversion"() {
        given: "a ZAR amount"
        BigDecimal zarAmount = new BigDecimal("185.00")

        when: "converting to USD"
        BigDecimal usdAmount = service.convertZarToUsd(zarAmount)

        then: "should get approximately 10 USD (185/18.5)"
        usdAmount > 9.5
        usdAmount < 10.5
    }

    void "test convertZarToUsd with zero amount"() {
        given: "zero ZAR"
        BigDecimal zarAmount = BigDecimal.ZERO

        when: "converting to USD"
        BigDecimal usdAmount = service.convertZarToUsd(zarAmount)

        then: "should return zero USD"
        usdAmount == BigDecimal.ZERO
    }

    void "test convertZarToUsd with small amount"() {
        given: "a small ZAR amount"
        BigDecimal zarAmount = new BigDecimal("1.85")

        when: "converting to USD"
        BigDecimal usdAmount = service.convertZarToUsd(zarAmount)

        then: "should get approximately 0.10 USD"
        usdAmount > 0.08
        usdAmount < 0.12
    }
}
