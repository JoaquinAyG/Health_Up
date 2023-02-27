package study.project.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "fav_table")
data class Fav(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uuid")
    val uuid: Int = Random().nextInt() * 1000,

    @ColumnInfo(name = "id")
    var id: Int = -1,

    @ColumnInfo(name = "exerciseId")
    var exerciseId: Int = -1,

    ) {
    override fun toString(): String {
        return "Fav(uuid=$uuid, id=$id, exerciseId=$exerciseId)"
    }
}