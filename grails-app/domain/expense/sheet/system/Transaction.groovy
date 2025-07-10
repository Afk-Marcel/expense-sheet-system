package expense.sheet.system

class Transaction {
    // Transaction variables
    static belongsTo = [user: User]
    BigDecimal amount
    String description
    BigDecimal runningBalance
    Date dateCreated
    BigDecimal usdAmount

    static constraints = {
        amount nullable: false, min: 0.01, scale: 2
        description nullable: false, blank: false, size: 2..250
        runningBalance nullable: false, scale: 2
        usdAmount nullable: true, scale: 2
    }

    // Display format - shows transaction details with safe navigation for nulls
    String toString() {
        def usdDisplay = usdAmount ?: "Not calculated"
        return "User: ${user?.name ?: 'New'}, Amount: R${amount ?: 0} (USD: \$${usdDisplay ?: 0}), Description: ${description ?: 'New Transaction'}, Running Balance: R${runningBalance ?: 0}, Date: ${dateCreated ?: new Date()}"
    }
}