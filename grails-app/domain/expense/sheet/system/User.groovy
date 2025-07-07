package expense.sheet.system

class User {
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
        table 'app_user'
    }

    String toString() {
        return "User: ${name}\n Balance: R${startingBalance}\n Available: R${currentBalance}\n Date: ${dateCreated}"
    }
}
