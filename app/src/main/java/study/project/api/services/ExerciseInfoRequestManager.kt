package study.project.api.services

import study.project.api.client.API_EXERCISE_INFO_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.ExerciseInfoResponse
import java.net.URL

class ExerciseInfoRequestManager : ApiRequestManager<ExerciseInfoResponse> {
    override fun getId(id: Int): ExerciseInfoResponse.Results {
        return getUrlObject(URL("$API_EXERCISE_INFO_URL$id/$FORMAT_JSON"), ExerciseInfoResponse.Results::class.java)
    }

    override fun getAll(): MutableCollection<ExerciseInfoResponse> {
        val exerciseList = mutableListOf<ExerciseInfoResponse>()
        var exerciseInfoResponse = ExerciseInfoResponse(null, API_EXERCISE_INFO_URL + FORMAT_JSON, null, arrayListOf())
        do {
            exerciseInfoResponse = getUrlObject(URL(exerciseInfoResponse.next), ExerciseInfoResponse::class.java)
            exerciseList.add(exerciseInfoResponse)
        } while(exerciseInfoResponse.next != null)
        return exerciseList
    }
}