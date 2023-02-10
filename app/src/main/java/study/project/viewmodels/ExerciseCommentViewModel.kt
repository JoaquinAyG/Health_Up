package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseCommentView

class ExerciseCommentViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseCommentList = MutableLiveData<List<ExerciseCommentView>>()
    val exerciseCommentList: LiveData<List<ExerciseCommentView>>
        get() = _exerciseCommentList
    fun fetchData() {
        uiScope.launch {
            val apiResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseComments()
            }
            _exerciseCommentList.value = apiResponse
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

