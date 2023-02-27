package study.project.api.services

import study.project.api.client.API_EXERCISE_COMMENT_URL
import study.project.api.client.FORMAT_JSON
import study.project.api.models.responses.ExerciseCommentResponse
import java.net.URL

class ExerciseCommentRequestManager : ApiRequestManager<ExerciseCommentResponse> {
    override fun getId(id: Int): ExerciseCommentResponse.Results {
        return getUrlObject(
            URL("$API_EXERCISE_COMMENT_URL$id/$FORMAT_JSON"),
            ExerciseCommentResponse.Results::class.java
        )
    }

    override fun getAll(): MutableCollection<ExerciseCommentResponse> {
        val exerciseList = mutableListOf<ExerciseCommentResponse>()
        var exerciseCommentResponse = ExerciseCommentResponse(
            null,
            API_EXERCISE_COMMENT_URL + FORMAT_JSON,
            null,
            arrayListOf()
        )
        do {
            exerciseCommentResponse =
                getUrlObject(URL(exerciseCommentResponse.next), ExerciseCommentResponse::class.java)
            exerciseList.add(exerciseCommentResponse)
        } while (exerciseCommentResponse.next != null)
        return exerciseList
    }
}