package study.project.api.services

import study.project.api.client.API_EXERCISE_CATEGORY_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.ExerciseCategoryResponse
import java.net.URL

class ExerciseCategoryRequestManager: ApiRequestManager<ExerciseCategoryResponse> {
    override fun getId(id: Int): ExerciseCategoryResponse.Results {
        return getUrlObject(URL("$API_EXERCISE_CATEGORY_URL$id/$FORMAT_JSON"), ExerciseCategoryResponse.Results::class.java)
    }

    override fun getAll(): MutableCollection<ExerciseCategoryResponse> {
        val exerciseCategoryList = mutableListOf<ExerciseCategoryResponse>()
        var exerciseCategoryResponse = ExerciseCategoryResponse(null, API_EXERCISE_CATEGORY_URL + FORMAT_JSON, null, arrayListOf())
        do {
            exerciseCategoryResponse = getUrlObject(URL(exerciseCategoryResponse.next), ExerciseCategoryResponse::class.java)
            exerciseCategoryList.add(exerciseCategoryResponse)
        } while(exerciseCategoryResponse.next != null)
        return exerciseCategoryList
    }
}