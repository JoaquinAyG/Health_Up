package study.project.api.models.views

import study.project.api.models.responses.MuscleResponse

class MuscleView(
    var id: Int? = null,
    var name: String? = null,
    var nameEn: String? = null,
    var imageUrlMain: String? = null,
    var imageUrlSecondary: String? = null
) {
    fun fromResult(res: MuscleResponse.Results): MuscleView {
        id = res.id
        name = res.name
        nameEn = res.nameEn
        imageUrlMain = res.imageUrlMain
        imageUrlSecondary = res.imageUrlSecondary
        return this
    }

    override fun toString(): String {
        return "MuscleView(id=$id, name=$name, nameEn=$nameEn, imageUrlMain=$imageUrlMain, imageUrlSecondary=$imageUrlSecondary)"
    }
}