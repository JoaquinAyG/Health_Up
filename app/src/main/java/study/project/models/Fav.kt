package study.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Fav(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "exerciseId")
    var exerciseId: Int = -1,

) {
    override fun toString(): String {
        return "$id - $exerciseId"
    }
}