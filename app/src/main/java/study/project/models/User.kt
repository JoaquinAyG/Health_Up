package study.project.models

import java.io.Serializable

data class User(
    val id: Int = -1,
    val username: String = "",
    var password: String = "",
    var email: String = "",
    var age: Int = 0,
    val admin: Boolean = false,
) : Serializable {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', age=$age)"
    }
}