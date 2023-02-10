package study.project.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.ExerciseCategoryView
import study.project.api.models.views.ExerciseImageView
import study.project.api.models.views.ExerciseInfoView
import study.project.api.models.views.ExerciseView
import study.project.models.Exercise

class ExerciseViewModel: ViewModel() {
    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>>
        get() = _exerciseList
    fun fetchData() {
        uiScope.launch {
            val exerciseResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExercises()
            }
            val infoResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseInfo()
            }
            val categoryResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseCategories()
            }
            val imagesResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllExerciseImages()
            }

            val newList = parseData(exerciseResponse, infoResponse, categoryResponse, imagesResponse)
            _exerciseList.value = newList
        }
    }

    private fun parseData(exerciseResponse: List<ExerciseView>, infoResponse: List<ExerciseInfoView>, categoryResponse: List<ExerciseCategoryView>, imagesResponse: List<ExerciseImageView>): List<Exercise> {
        val newList = mutableListOf<Exercise>()
        for (exercise in exerciseResponse) {
            val info = infoResponse.find { it.id == exercise.id }
            val category = categoryResponse.find { it.id == exercise.category }
            val images = mutableListOf<String>()
            info?.images?.forEach { image ->
                val newImage = imagesResponse.find { it.id == image.id }
                if (newImage != null) {
                    newImage.image?.let { images.add(it) }
                }
            }
            val newExercise = Exercise(
                id = exercise.id ?: 0,
                name = exercise.name ?: "No name",
                imageUrlMain = images.first(),
                imageUrlSecondary = images.size.let { if (it > 1) images[1] else "" },
                descriptionEn = info?.description ?: "No description",
                muscles = exercise.muscles,
                category = category?.name ?: "No category",
                variations = exercise.variations
            )
            newList.add(newExercise)
        }
        return newList
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

