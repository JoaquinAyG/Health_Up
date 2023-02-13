package study.project.api.models

import com.google.gson.annotations.SerializedName

data class Comments (

    @SerializedName("id"       ) var id       : Int?    = null,
    @SerializedName("exercise" ) var exercise : Int?    = null,
    @SerializedName("comment"  ) var comment  : String? = null

)