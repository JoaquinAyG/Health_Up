package study.project.api.models.responses

import com.google.gson.annotations.SerializedName


data class ExerciseImageResponse (

  @SerializedName("count"    ) var count    : Int?               = null,
  @SerializedName("next"     ) var next     : String?            = null,
  @SerializedName("previous" ) var previous : String?            = null,
  @SerializedName("results"  ) var results  : ArrayList<Results> = arrayListOf()

) : BaseResponse() {

  data class Results (

    @SerializedName("id"             ) var id            : Int?              = null,
    @SerializedName("uuid"           ) var uuid          : String?           = null,
    @SerializedName("exercise_base"  ) var exerciseBase  : Int?              = null,
    @SerializedName("image"          ) var image         : String?           = null,
    @SerializedName("is_main"        ) var isMain        : Boolean?          = null,
    @SerializedName("style"          ) var style         : String?           = null,
    @SerializedName("license"        ) var license       : Int?              = null,
    @SerializedName("license_author" ) var licenseAuthor : String?           = null,
    @SerializedName("author_history" ) var authorHistory : ArrayList<String> = arrayListOf()

  ): BaseResponse.Results()

}