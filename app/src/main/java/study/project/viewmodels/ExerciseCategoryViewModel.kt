package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseCategoryView

class ExerciseCategoryViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseCategoryList = MutableLiveData<List<ExerciseCategoryView>>()
    val exerciseCategoryList: LiveData<List<ExerciseCategoryView>>
        get() = _exerciseCategoryList
    fun fetchData() {
        uiScope.launch {
            val apiResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseCategories()
            }
            _exerciseCategoryList.value = apiResponse
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

