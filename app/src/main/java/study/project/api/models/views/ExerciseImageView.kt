package study.project.api.models.views

import study.project.api.models.responses.ExerciseImageResponse

class ExerciseImageView(
    var id: Int? = null,
    var uuid: String? = null,
    var image: String? = null,
    var isMain: Boolean? = null,
    var style: String? = null
) {
    fun fromResult(res: ExerciseImageResponse.Results): ExerciseImageView {
        id = res.id
        uuid = res.uuid
        image = res.image
        isMain = res.isMain
        style = res.style
        return this
    }

    override fun toString(): String {
        return "ExerciseImageView(id=$id, uuid=$uuid, image=$image, isMain=$isMain, style=$style)"
    }
}