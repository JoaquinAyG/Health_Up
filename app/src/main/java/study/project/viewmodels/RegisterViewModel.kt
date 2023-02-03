package study.project.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import study.project.models.User

class RegisterViewModel : ViewModel() {
    val user = MutableLiveData<User?>()

    init {
        user.value = User()
    }

    fun updateId(id: Int) {
        val u = user.value
        if (u != null) {
            u.id = id
        }
        user.value = u
    }
    fun updateName(name: String) {
        val u = user.value
        if (u != null) {
            u.username = name
        }
        user.value = u
    }

    fun updateEmail(email: String) {
        val u = user.value
        if (u != null) {
            u.email = email
        }
        user.value = u
    }

    fun updatePassword(password: String) {
        val u = user.value
        if (u != null) {
            u.password = password
        }
        user.value = u
    }

    fun updateAge(age: Int) {
        val u = user.value
        if (u != null) {
            u.age = age
        }
        user.value = u
    }

    fun updateCapableDays(capableDays: List<Int>) {
        val u = user.value
        if (u != null) {
            u.capableDays = capableDays
        }
        user.value = u
    }

    fun updateHeight(height: Int) {
        val u = user.value
        if (u != null) {
            u.height = height
        }
        user.value = u
    }

    fun updateWeight(weight: Double) {
        val u = user.value
        if (u != null) {
            u.weight = weight
        }
        user.value = u
    }

    fun updateGender(gender: String) {
        val u = user.value
        if (u != null) {
            u.gender = gender
        }
        user.value = u
    }
}