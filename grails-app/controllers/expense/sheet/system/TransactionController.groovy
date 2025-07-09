package expense.sheet.system

import grails.gorm.transactions.TransactionService
import java.text.SimpleDateFormat

class TransactionController {
    static scaffold = Transaction

    TransactionService transactionService

    def save(Transaction transactionInstance) {
        def user = User.get(params.user.id)
        def transaction = transactionService.createTransaction(user, transactionInstance.amount, transactionInstance.description)

        if(transaction) {
            flash.message = "Transaction was successful"
            redirect(action: 'index')
        } else {
            flash.error = "Transaction was unsuccessful"
            redirect(action: 'create')
        }
    }

    def exportCsv() {
        def userId = params.userId
        def user = User.get(userId)

        if (!user) {
            flash.error = "User not found"
            redirect(action: 'index')
            return
        }

        def transactions = Transaction.findAllByUser(user, [sort: 'dateCreated'])

        def csvContent = new StringBuilder()

        csvContent.append("Date, Description, Amount, Running Balance\n")

        transactions.each {
            def dateStr = transaction.dateCreated.format('yyyy-MM-dd')
            csvContent.append("${dateStr}, ${transaction.description}, ${transaction.amount}, ${transaction.runningBalance}\n")
            response.contentType = "text/csv"
            response.setHeader("Content-Disposition", "attachment; filename=\"${user.name}_transactions.csv\"")
            render(text: csvContent.toString())
        }
    }
}