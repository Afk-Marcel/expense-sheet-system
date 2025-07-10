package expense.sheet.system

import grails.gorm.transactions.Transactional

@Transactional
class UserService {

    // Creates a new user with starting balance
    def createUser(String name, BigDecimal startingBalance) {
        def user = new User()
        user.name = name
        user.startingBalance = startingBalance
        user.currentBalance = startingBalance

        // Save user to database
        if (user.save(flush: true)) {
            return user
        } else {
            return null
        }
    }
    // Finds user by name (case-insensitive)
    def findUserByName(String name) {
        return User.findByNameIlike(name)
    }

    // Gets all users in the system
    def getAllUsers() {
        return User.list()
    }

    // Checks if any users exist in the system
    def hasUsers() {
        return User.count() > 0
    }
}