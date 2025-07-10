package expense.sheet.system

import grails.converters.JSON
import groovy.util.logging.Slf4j

@Slf4j
class CurrencyService {

    // Cache exchange rate for 1 hour
    private static BigDecimal cachedRate = null
    private static Date lastFetch = null

    // Converts ZAR amount to USD
    BigDecimal convertZarToUsd(BigDecimal zarAmount) {
        def rate = getExchangeRate()
        return zarAmount / rate
    }

    // Gets current USD/ZAR exchange rate from fixer.io API with caching
    private BigDecimal getExchangeRate() {
        // Check if cached rate is still valid (less than 1 hour old)
        if (cachedRate && lastFetch && (new Date().time - lastFetch.time) < 3600000) {
            return cachedRate
        }

        try {
            // Make HTTP call to fixer.io
            def url = "http://data.fixer.io/api/latest?access_key=a25fe7a20e70a037cc316698d81f17b2&base=EUR&symbols=USD,ZAR"
            def response = new URL(url).getText()

            // Parse JSON response from API
            def jsonResponse = JSON.parse(response)

            // Calculate USD/ZAR rate (ZAR per 1 USD)
            def rates = jsonResponse.rates
            def usdRate = rates['USD'] as BigDecimal
            def zarRate = rates['ZAR'] as BigDecimal
            def usdToZarRate = zarRate / usdRate

            // Cache the result for future use
            cachedRate = usdToZarRate
            lastFetch = new Date()

            return cachedRate

        } catch (Exception e) {
            // If API fails, use a fallback rate
            log.error("Failed to fetch exchange rate: ${e.message}")
            return new BigDecimal("18.5")
        }
    }
}