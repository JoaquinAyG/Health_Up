package study.project.api.models.views

import study.project.api.models.responses.ExerciseCommentResponse

class ExerciseCommentView(
    var id: Int? = null,
    var exercise: Int? = null,
    var comment: String? = null
) {
    fun fromResult(res: ExerciseCommentResponse.Results): ExerciseCommentView {
        id = res.id
        exercise = res.exercise
        comment = res.comment
        return this
    }

    override fun toString(): String {
        return "ExerciseCommentView(id=$id, exercise=$exercise, comment=$comment)"
    }
}