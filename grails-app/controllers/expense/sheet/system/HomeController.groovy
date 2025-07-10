package expense.sheet.system

class HomeController {

    UserService userService

    def index() {
        // Check if any users exist using service
        if (!userService.hasUsers()) {
            redirect(action: 'setupUser')
        } else {
            // Get all users using service
            def users = userService.getAllUsers()
            [users: users]
        }
    }

    def setupUser() {
        // Display the user setup form
    }

    def createUser() {
        // Get form parameters
        def name = params.name as String
        def startingBalance = params.startingBalance as BigDecimal

        // Use service to create user
        def user = userService.createUser(name, startingBalance)

        if (user) {
            // User created successfully
            flash.message = "Welcome ${user.name}! Your account is ready."
            redirect(action: 'index')
        } else {
            // User creation failed
            flash.error = "Please fix the errors and try again."
            redirect(action: 'setupUser')
        }
    }
}