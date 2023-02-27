package study.project.api.models

import com.google.gson.annotations.SerializedName


data class Language(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("short_name") var shortName: String? = null,
    @SerializedName("full_name") var fullName: String? = null

)