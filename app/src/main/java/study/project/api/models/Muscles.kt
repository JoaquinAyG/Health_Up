package study.project.api.models

import com.google.gson.annotations.SerializedName

data class Muscles(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("name_en") var nameEn: String? = null,
    @SerializedName("is_front") var isFront: Boolean? = null,
    @SerializedName("image_url_main") var imageUrlMain: String? = null,
    @SerializedName("image_url_secondary") var imageUrlSecondary: String? = null

)