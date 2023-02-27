package study.project.api.models.responses

import com.google.gson.annotations.SerializedName


data class ExerciseResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

) : BaseResponse() {
    data class Results(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("uuid") var uuid: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("exercise_base") var exerciseBase: Int? = null,
        @SerializedName("description") var description: String? = null,
        @SerializedName("creation_date") var creationDate: String? = null,
        @SerializedName("category") var category: Int? = null,
        @SerializedName("muscles") var muscles: ArrayList<String> = arrayListOf(),
        @SerializedName("muscles_secondary") var musclesSecondary: ArrayList<String> = arrayListOf(),
        @SerializedName("equipment") var equipment: ArrayList<Int> = arrayListOf(),
        @SerializedName("language") var language: Int? = null,
        @SerializedName("license") var license: Int? = null,
        @SerializedName("license_author") var licenseAuthor: String? = null,
        @SerializedName("variations") var variations: ArrayList<Int> = arrayListOf(),
        @SerializedName("author_history") var authorHistory: ArrayList<String> = arrayListOf()

    ) : BaseResponse.Results()
}