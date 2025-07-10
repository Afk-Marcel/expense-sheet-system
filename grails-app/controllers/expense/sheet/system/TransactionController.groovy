package expense.sheet.system

import java.text.SimpleDateFormat

class TransactionController {
    static scaffold = Transaction

    // Grails auto-injects these services
    TransactionService transactionService
    CurrencyService currencyService

    def save(Transaction transactionInstance) {
        // Get the user from form parameters
        def userId = params.user.id as Long
        def user = User.get(userId)

        // Null check for safety
        if (!user) {
            flash.error = "User not found"
            redirect(action: 'create')
            return
        }

        // Use service to create transaction (handles balance calculations)
        def transaction = transactionService.createTransaction(user, transactionInstance.amount, transactionInstance.description)

        if(transaction) {
            // Transaction created successfully
            flash.message = "Transaction was successful"
            redirect(action: 'index')
        } else {
            // Transaction failed validation or save
            flash.error = "Transaction was unsuccessful"
            redirect(action: 'create')
        }
    }

    def exportCsv() {
        // Get user ID from URL parameters
        def userId = params.userId as Long
        def user = User.get(userId)

        // Validate user exists
        if (!user) {
            flash.error = "User not found"
            redirect(action: 'index')
            return
        }

        // Get all transactions for this user, sorted by date
        def transactions = Transaction.findAllByUser(user, [sort: 'dateCreated'])

        // Build CSV content
        def csvContent = new StringBuilder()

        // Add CSV header row
        csvContent.append("Date,Description,Amount (ZAR),Running Balance (ZAR),Amount (USD)\n")

        // Create date formatter for consistent date format
        def dateFormatter = new SimpleDateFormat('yyyy-MM-dd')

        // Add each transaction as a CSV row
        transactions.each { transaction ->
            // Calculate USD if not already calculated
            if (!transaction.usdAmount) {
                transaction.usdAmount = currencyService.convertZarToUsd(transaction.amount)
            }

            // Format date safely (handle potential nulls)
            def dateStr = transaction.dateCreated ? dateFormatter.format(transaction.dateCreated) : 'N/A'

            // Add transaction row to CSV
            csvContent.append("${dateStr},${transaction.description},${transaction.amount},${transaction.runningBalance},${transaction.usdAmount}\n")
        }

        // Set response headers for file download
        response.contentType = "text/csv"
        response.setHeader("Content-Disposition", "attachment; filename=\"${user.name}_transactions.csv\"")

        // Return CSV content as file download
        render csvContent.toString()
    }
}