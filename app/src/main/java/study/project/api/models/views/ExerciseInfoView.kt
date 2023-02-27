package study.project.api.models.views

import models.Images
import study.project.api.models.*
import study.project.api.models.responses.ExerciseInfoResponse

class ExerciseInfoView(
    var id: Int? = null,
    var name: String? = null,
    var uuid: String? = null,
    var description: String? = null,
    var category: Category? = Category(),
    var muscles: ArrayList<Muscles> = arrayListOf(),
    var musclesSecondary: ArrayList<Muscles> = arrayListOf(),
    var equipment: ArrayList<Equipment> = arrayListOf(),
    var images: ArrayList<Images> = arrayListOf(),
    var videos: ArrayList<Videos> = arrayListOf(),
    var comments: ArrayList<Comments> = arrayListOf(),
    var variations: ArrayList<Int> = arrayListOf()
) {
    fun fromResult(res: ExerciseInfoResponse.Results): ExerciseInfoView {
        id = res.id
        name = res.name
        uuid = res.uuid
        description = res.description
        category = res.category
        muscles = res.muscles
        musclesSecondary = res.musclesSecondary
        equipment = res.equipment
        images = res.images
        videos = res.videos
        comments = res.comments
        variations = res.variations
        return this
    }

    override fun toString(): String {
        return "ExerciseInfoView(id=$id, name=$name, uuid=$uuid, description=$description, category=$category, muscles=$muscles, musclesSecondary=$musclesSecondary, equipment=$equipment, images=$images, videos=$videos, comments=$comments, variations=$variations)"
    }
}