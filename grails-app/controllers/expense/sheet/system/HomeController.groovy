package expense.sheet.system


class HomeController {
    def userCount = User.count()

    def index() {
        if(userCount == 0) {
            redirect(action: 'setupUser')
        } else {
            def users = User.List()
            [users: users]
        }
    }

    def setupUser() {
        [message: "Welcome! Let's set up your account."]
    }

    def createUser() {
        def name = params.name
        def startingBalance = params.startingBalance as BigDecimal
        def user = new User()
        user.name = name
        user.startingBalance = startingBalance
        user.currentBalance = startingBalance

        if (user.save(flush: true)) {
            flash.message = "Welcome ${user.name}! Your account is ready."
            redirect(action: 'index')
        } else {
            flash.error = "Please fix the errors and try again."
            redirect(action: 'setupUser')
        }
    }
}