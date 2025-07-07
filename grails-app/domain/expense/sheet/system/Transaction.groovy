package expense.sheet.system

class Transaction {
    static belongsTo = [user: User]
    BigDecimal amount
    String description
    BigDecimal runningBalance
    Date dateCreated

    static constraints = {
        amount nullable: false, min: 0.01, scale: 2
        description nullable: false, blank: false, size: 2..250
        runningBalance nullable: false, scale: 2
    }

    String toString() {
        return "User: ${user?.name ?: 'New'}\n Transaction Amount: R${amount ?: 0}\n Description: ${description ?: 'New Transaction'}\n Running Balance: R${runningBalance ?: 0}\n Date: ${dateCreated ?: new Date()}"
    }
}