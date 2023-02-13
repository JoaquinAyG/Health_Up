package study.project.api.models

import com.google.gson.annotations.SerializedName

data class Videos (

    @SerializedName("id"                 ) var id               : Int?              = null,
    @SerializedName("uuid"               ) var uuid             : String?           = null,
    @SerializedName("exercise_base"      ) var exerciseBase     : Int?              = null,
    @SerializedName("exercise_base_uuid" ) var exerciseBaseUuid : String?           = null,
    @SerializedName("video"              ) var video            : String?           = null,
    @SerializedName("is_main"            ) var isMain           : Boolean?          = null,
    @SerializedName("size"               ) var size             : Int?              = null,
    @SerializedName("duration"           ) var duration         : String?           = null,
    @SerializedName("width"              ) var width            : Int?              = null,
    @SerializedName("height"             ) var height           : Int?              = null,
    @SerializedName("codec"              ) var codec            : String?           = null,
    @SerializedName("codec_long"         ) var codecLong        : String?           = null,
    @SerializedName("license"            ) var license          : Int?              = null,
    @SerializedName("license_author"     ) var licenseAuthor    : String?           = null,
    @SerializedName("author_history"     ) var authorHistory    : ArrayList<String> = arrayListOf()

)