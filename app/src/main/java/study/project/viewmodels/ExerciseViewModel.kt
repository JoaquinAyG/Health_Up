package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseView

class ExerciseViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseList = MutableLiveData<List<ExerciseView>>()
    val exerciseList: LiveData<List<ExerciseView>>
        get() = _exerciseList
    fun fetchData() {
        uiScope.launch {
            val apiResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExercises()
            }
            _exerciseList.value = apiResponse
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

