package app.jordansilva.domain.model

data class User(var id: String) {
    var login: String = ""
    var password: String = ""
    var name: String = ""
    var avatar: String? = null

    constructor(id: String, login: String, password: String) : this(id) {
        this.login = login
        this.password = password
    }
}