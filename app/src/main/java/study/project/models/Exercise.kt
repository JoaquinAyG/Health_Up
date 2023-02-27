package study.project.models

import java.io.Serializable

data class Exercise(

    var id: Int = 0,
    var name: String = "",
    var nameEn: String = "",
    var imageUrlMain: String = "",
    var imageUrlSecondary: String = "",
    var descriptionEn: String = "",
    var muscles: List<String> = listOf(),
    var category: String = "",
    var variations: List<Int> = listOf(),
    var favourite: Boolean = false
) : Serializable