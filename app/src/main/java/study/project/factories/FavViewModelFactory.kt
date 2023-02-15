package study.project.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import study.project.repos.FavRepository
import study.project.viewmodels.FavViewModel

class FavViewModelFactory(private val repository: FavRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}