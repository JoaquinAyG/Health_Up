package study.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "user_table")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = -1,

    @ColumnInfo(name = "username")
    val username: String = "",

    @ColumnInfo(name = "password")
    var password: String = "",

    @ColumnInfo(name = "email")
    var email: String = "",

    @ColumnInfo(name = "age")
    var age: Int = 0,

    @ColumnInfo(name = "admin")
    val admin: Boolean = false,

) : Serializable {
    override fun toString(): String {
        return "User(id=$id, username='$username', password='$password', email='$email', age=$age)"
    }
}