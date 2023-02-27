package models

import com.google.gson.annotations.SerializedName

data class Images(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("uuid") var uuid: String? = null,
    @SerializedName("exercise_base") var exerciseBase: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("is_main") var isMain: Boolean? = null,
    @SerializedName("style") var style: String? = null,
    @SerializedName("license") var license: Int? = null,
    @SerializedName("license_author") var licenseAuthor: String? = null,
    @SerializedName("author_history") var authorHistory: ArrayList<String> = arrayListOf()

)