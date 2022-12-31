package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import study.project.models.User
import study.project.repos.UserRepository

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers: LiveData<List<User>> = repository.allUsers.asLiveData()

    fun insert(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun delete(user: User) = viewModelScope.launch {
        repository.delete(user)
    }

    fun update(user: User) = viewModelScope.launch {
        repository.update(user)
    }
}