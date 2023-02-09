package study.project.api.models.responses

import com.google.gson.annotations.SerializedName


data class MuscleResponse (

  @SerializedName("count"    ) var count    : Int?               = null,
  @SerializedName("next"     ) var next     : String?            = null,
  @SerializedName("previous" ) var previous : String?            = null,
  @SerializedName("results"  ) var results  : ArrayList<Results> = arrayListOf()

) : BaseResponse() {
  data class Results (

    @SerializedName("id"                  ) var id                : Int?     = null,
    @SerializedName("name"                ) var name              : String?  = null,
    @SerializedName("name_en"             ) var nameEn            : String?  = null,
    @SerializedName("is_front"            ) var isFront           : Boolean? = null,
    @SerializedName("image_url_main"      ) var imageUrlMain      : String?  = null,
    @SerializedName("image_url_secondary" ) var imageUrlSecondary : String?  = null

  ): BaseResponse.Results()
}