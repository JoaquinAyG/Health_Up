package study.project.models

class UserProfile {

    companion object {
        var instance = User()
    }

    fun setInstance(user: User) {
        instance = user
    }
}