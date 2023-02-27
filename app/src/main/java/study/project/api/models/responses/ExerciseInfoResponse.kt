package study.project.api.models.responses

import com.google.gson.annotations.SerializedName
import models.*
import study.project.api.models.*

data class ExerciseInfoResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

) : BaseResponse() {
    data class Results(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("aliases") var aliases: ArrayList<Aliases> = arrayListOf(),
        @SerializedName("uuid") var uuid: String? = null,
        @SerializedName("exercise_base_id") var exerciseBaseId: Int? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("creation_date") var creationDate: String? = null,
        @SerializedName("category") var category: Category? = Category(),
        @SerializedName("muscles") var muscles: ArrayList<Muscles> = arrayListOf(),
        @SerializedName("muscles_secondary") var musclesSecondary: ArrayList<Muscles> = arrayListOf(),
        @SerializedName("equipment") var equipment: ArrayList<Equipment> = arrayListOf(),
        @SerializedName("language") var language: Language? = Language(),
        @SerializedName("license") var license: License? = License(),
        @SerializedName("license_author") var licenseAuthor: String? = null,
        @SerializedName("images") var images: ArrayList<Images> = arrayListOf(),
        @SerializedName("videos") var videos: ArrayList<Videos> = arrayListOf(),
        @SerializedName("comments") var comments: ArrayList<Comments> = arrayListOf(),
        @SerializedName("variations") var variations: ArrayList<Int> = arrayListOf(),
        @SerializedName("author_history") var authorHistory: ArrayList<String> = arrayListOf()

    ) : BaseResponse.Results()
}