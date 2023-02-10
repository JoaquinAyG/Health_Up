package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseImageView

class ImageViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseImageList = MutableLiveData<List<ExerciseImageView>>()
    val exerciseImageList: LiveData<List<ExerciseImageView>>
        get() = _exerciseImageList
    fun fetchData() {
        uiScope.launch {
            val apiResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseImages()
            }
            _exerciseImageList.value = apiResponse
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

