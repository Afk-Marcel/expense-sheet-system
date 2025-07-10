package expense.sheet.system

class User {
    // User variables
    String name
    BigDecimal startingBalance
    BigDecimal currentBalance
    Date dateCreated

    static constraints = {
        name nullable: false, blank:false, unique: true, size: 2..50
        startingBalance nullable: false, min: 0.0, scale: 2
        currentBalance nullable: false, scale: 2
    }

    static mapping = {
        // Avoid SQL reserved word "user"
        table 'app_user'
    }

    // Display format for user objects - shows key financial info
    String toString() {
        return "User: ${name}, Balance: R${startingBalance}, Available: R${currentBalance}, Date: ${dateCreated}"
    }
}
