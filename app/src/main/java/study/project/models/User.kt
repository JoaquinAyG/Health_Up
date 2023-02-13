package study.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "username")
    var username: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "weight")
    var weight: Double = 0.0,

    @ColumnInfo(name = "height")
    var height: Int = 0,

    @ColumnInfo(name = "gender")
    var gender: String = "",

    @ColumnInfo(name = "capableDays")
    var capableDays: String = "",

    @ColumnInfo(name = "age")
    var age: Int = 0,

    @ColumnInfo(name = "admin")
    var admin: Boolean = false,

    @ColumnInfo(name = "favourites")
    var favourites: String = "",

    @Ignore
    var favoriteList: MutableList<Int> = mutableListOf()

    ) : Serializable {

    init {
        if (favourites.isNotEmpty()) {
            val favList = favourites.split(",")
            favList.forEach {
                favoriteList.add(it.toInt())
            }
        }
    }

    @Ignore
    fun addFavoriteExercise(exercise: Int) {
        if (!favoriteList.contains(exercise)) {
            favoriteList.add(exercise)
            favourites = favoriteList.joinToString(",")
        }
    }
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', weight=$weight, height=$height, gender='$gender', capableDays='$capableDays', age=$age, admin=$admin, favourites=$favourites)"
    }
}