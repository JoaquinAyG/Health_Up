package study.project.models

import study.project.activities.ExerciseActivity
import java.io.Serializable

data class Exercise(

    var id                : Int     = 0,
    var name              : String  = "Exercise",
    var nameEn            : String  = "Excercise",
    var imageUrlMain      : String  = "https://pbs.twimg.com/media/EcAED4eWkAAsEZZ.jpg",
    var imageUrlSecondary : String  = "https://pbs.twimg.com/media/EcAED4eWkAAsEZZ.jpg",
    var descriptionEn     : String  = "This is the description of the excersise, enjoy nothing",
    var muscles          : List<String> = listOf(),
    var category: String = "Category",
    var variations: List<Int> = listOf(),
) : Serializable