package study.project.api.models

import com.google.gson.annotations.SerializedName


data class License (

  @SerializedName("id"         ) var id        : Int?    = null,
  @SerializedName("full_name"  ) var fullName  : String? = null,
  @SerializedName("short_name" ) var shortName : String? = null,
  @SerializedName("url"        ) var url       : String? = null

)