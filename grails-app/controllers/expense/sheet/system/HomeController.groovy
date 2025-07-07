package expense.sheet.system


class HomeController {
    static belongsTo = [user: User]
    def index() {
        if(User.count().equals(0)){
            redirect(action: 'setupUser')
        } else {

        }

    }
}