package study.project.api.models.views

import study.project.api.models.responses.ExerciseResponse

data class ExerciseView (

        var id               : Int?              = null,
        var uuid             : String?           = null,
        var name             : String?           = null,
        var description      : String?           = null,
        var category         : Int?              = null,
        var muscles          : ArrayList<String> = arrayListOf(),
        var musclesSecondary : ArrayList<String> = arrayListOf(),
        var licenseAuthor    : String?           = null,
        var variations       : ArrayList<Int>    = arrayListOf(),
        var authorHistory    : ArrayList<String> = arrayListOf()

    ) {
    fun fromResult(res: ExerciseResponse.Results) : ExerciseView {
        id               = res.id
        uuid             = res.uuid
        name             = res.name
        description      = res.description
        category         = res.category
        muscles          = res.muscles
        musclesSecondary = res.musclesSecondary
        licenseAuthor    = res.licenseAuthor
        variations       = res.variations
        authorHistory    = res.authorHistory
        return this
    }

    override fun toString(): String {
        return "ExerciseView(id=$id, uuid=$uuid, name=$name, description=$description, category=$category, muscles=$muscles, musclesSecondary=$musclesSecondary, licenseAuthor=$licenseAuthor, variations=$variations, authorHistory=$authorHistory)"
    }
}