package study.project.viewmodels

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import study.project.models.Fav
import study.project.models.User
import study.project.repos.FavRepository

class FavViewModel(private val repository: FavRepository) : ViewModel() {

    private val mutableFav = MutableLiveData<Fav>()
    val fav: LiveData<Fav>
        get() = mutableFav

    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    val allFavs: LiveData<List<Fav>> = repository.allFavs.asLiveData()
    fun loadSingle(id: String) = viewModelScope.launch {
        mutableFav.value = repository.loadSingle(id).asLiveData().value
    }

    fun insert(fav: Fav) = viewModelScope.launch {
        repository.insert(fav)
        _status.postValue("CHANGE")
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

    fun delete(fav: Fav) = viewModelScope.launch {
        repository.delete(fav)
        _status.postValue("CHANGE")
    }

    fun update(fav: Fav) = viewModelScope.launch {
        repository.update(fav)
    }
}
