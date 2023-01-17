package study.project.models

import java.io.Serializable

data class Exercise(

    var id                : Int?     = null,
    var name              : String?  = null,
    var nameEn            : String?  = null,
    var isFront           : Boolean? = null,
    var imageUrlMain      : String?  = null,
    var imageUrlSecondary : String?  = null,
    var descriptionEn     : String?  = null,
    var muscles          : String?  = null,
    var category: String? = null,
    var variations: List<Int>? = null,
) : Serializable