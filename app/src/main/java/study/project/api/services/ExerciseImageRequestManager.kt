package study.project.api.services

import study.project.api.client.API_EXERCISE_IMAGE_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.ExerciseImageResponse
import java.net.URL

class ExerciseImageRequestManager : ApiRequestManager<ExerciseImageResponse> {
    override fun getId(id: Int): ExerciseImageResponse.Results {
        return getUrlObject(URL("$API_EXERCISE_IMAGE_URL$id/$FORMAT_JSON"), ExerciseImageResponse.Results::class.java)
    }

    override fun getAll(): MutableCollection<ExerciseImageResponse> {
        val exerciseList = mutableListOf<ExerciseImageResponse>()
        var exerciseImageResponse = ExerciseImageResponse(null, API_EXERCISE_IMAGE_URL + FORMAT_JSON, null, arrayListOf())
        do {
            exerciseImageResponse = getUrlObject(URL(exerciseImageResponse.next), ExerciseImageResponse::class.java)
            exerciseList.add(exerciseImageResponse)
        } while(exerciseImageResponse.next != null)
        return exerciseList
    }
}