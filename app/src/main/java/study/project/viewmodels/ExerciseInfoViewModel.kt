package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseInfoView

class ExerciseInfoViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseInfoList = MutableLiveData<List<ExerciseInfoView>>()
    val exerciseInfoList: LiveData<List<ExerciseInfoView>>
        get() = _exerciseInfoList
    fun fetchData() {
        uiScope.launch {
            val apiResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseInfo()
            }
            _exerciseInfoList.value = apiResponse
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

