package study.project.api.services

import study.project.api.client.API_EXERCISE_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.ExerciseResponse
import java.net.URL

class ExerciseRequestManager : ApiRequestManager<ExerciseResponse> {
    
    override fun getId(id: Int): ExerciseResponse.Results {
        return getUrlObject(URL("$API_EXERCISE_URL$id/$FORMAT_JSON"), ExerciseResponse.Results::class.java)
    }

    override fun getAll(): MutableCollection<ExerciseResponse> {
        val exerciseList = mutableListOf<ExerciseResponse>()
        var exerciseResponse = ExerciseResponse(null, API_EXERCISE_URL + FORMAT_JSON, null, arrayListOf())
        do {
            exerciseResponse = getUrlObject(URL(exerciseResponse.next), ExerciseResponse::class.java)
            exerciseList.add(exerciseResponse)
        } while(exerciseResponse.next != null)
        return exerciseList
    }
}