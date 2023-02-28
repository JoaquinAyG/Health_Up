package study.project.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import study.project.api.client.HealthApiManagerClient
import study.project.api.models.views.*
import study.project.models.AppStatus
import study.project.models.Exercise

class ExerciseViewModel : ViewModel() {
    private val viewModelJob = Job()
    var fetched = false
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val _exerciseList = MutableLiveData<List<Exercise>>()
    val exerciseList: LiveData<List<Exercise>>
        get() = _exerciseList

    val categoryList = listOf("Abs", "Arms", "Back", "Chest", "Legs", "Shoulders", "Cardio")
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
        get() = _status

    private val mutableExerciseStatus = MutableLiveData<String>()
    val exerciseStatus: LiveData<String>
        get() = mutableExerciseStatus

    fun fetchData() {
        uiScope.launch {
            _status.value = AppStatus.LOADING
            Log.i("ExerciseViewModel", "Fetching data...")
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
            val muscleResponse = withContext(Dispatchers.IO) {
                HealthApiManagerClient.getAllMuscles()
            }


            Log.i("ExerciseViewModel", "Parsing data...")

            val newList = parseData(
                exerciseResponse,
                infoResponse,
                categoryResponse,
                imagesResponse,
                muscleResponse
            )
            _exerciseList.value = newList

            Log.i("ExerciseViewModel", "Data generated")
            _status.value = AppStatus.SUCCESS
            fetched = true
        }
    }

    private fun parseData(
        exerciseResponse: List<ExerciseView>,
        infoResponse: List<ExerciseInfoView>,
        categoryResponse: List<ExerciseCategoryView>,
        imagesResponse: List<ExerciseImageView>,
        muscleResponse: List<MuscleView>
    ): List<Exercise> {
        val newList = mutableListOf<Exercise>()
        for (exercise in exerciseResponse) {
            val info = infoResponse.find { it.id == exercise.id }
            val category = categoryResponse.find { it.id == exercise.category }
            val images = mutableListOf<String>()
            info?.images?.forEach { image ->
                val newImage = imagesResponse.find { it.id == image.id }
                if (newImage != null) {
                    newImage.image?.let { images.add(it.replace("_", "-")) }
                }
            }
            val muscles = mutableListOf<String>()
            exercise.muscles.forEach { muscle ->
                val newMuscle = muscleResponse.find { it.id == muscle.toInt() }
                muscle.let {
                    if (newMuscle != null) {
                        newMuscle.nameEn?.let { it1 -> muscles.add(it1) }
                    }
                }
            }
            val newExercise = Exercise(
                id = exercise.id ?: 0,
                name = exercise.name ?: "No name",
                imageUrlMain = images.firstOrNull() ?: "",
                imageUrlSecondary = images.size.let { if (it > 1) images[1] else "" },
                descriptionEn = info?.description ?: "No description",
                muscles = muscles,
                category = category?.name ?: "No category",
                variations = exercise.variations
            )

            if (newExercise.imageUrlMain.isNotEmpty() || newExercise.imageUrlSecondary.isNotEmpty()) {
                newList.add(newExercise)
            }
        }
        return newList
    }

    fun updateExerciseStatus() {
        mutableExerciseStatus.postValue("CHANGE")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}

