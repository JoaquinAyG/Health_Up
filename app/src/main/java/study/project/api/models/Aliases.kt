package study.project.api.models

import com.google.gson.annotations.SerializedName

data class Aliases (

    @SerializedName("id"    ) var id    : Int?    = null,
    @SerializedName("alias" ) var alias : String? = null

)