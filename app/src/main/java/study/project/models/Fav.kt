package study.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Fav(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "favourite")
    var favourite: String = "",
) {
    override fun toString(): String {
        return "$id $favourite"
    }
}