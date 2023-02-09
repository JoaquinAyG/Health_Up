package study.project.api.models.views

import study.project.api.models.responses.ExerciseCategoryResponse

class ExerciseCategoryView(
    var id: Int? = null,
    var name: String? = null
) {
    fun fromResult(res: ExerciseCategoryResponse.Results): ExerciseCategoryView {
        id = res.id
        name = res.name
        return this
    }

    override fun toString(): String {
        return "ExerciseCategoryView(id=$id, name=$name)"
    }
}