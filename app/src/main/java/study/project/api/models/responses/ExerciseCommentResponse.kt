package study.project.api.models.responses

import com.google.gson.annotations.SerializedName


data class ExerciseCommentResponse(

    @SerializedName("count") var count: Int? = null,
    @SerializedName("next") var next: String? = null,
    @SerializedName("previous") var previous: String? = null,
    @SerializedName("results") var results: ArrayList<Results> = arrayListOf()

) : BaseResponse() {

    data class Results(

        @SerializedName("id") var id: Int? = null,
        @SerializedName("exercise") var exercise: Int? = null,
        @SerializedName("comment") var comment: String? = null

    ) : BaseResponse.Results()

}