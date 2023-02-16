package study.project.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import study.project.models.Fav
import study.project.models.User

@Dao
interface FavDao {

    @Query("SELECT * FROM fav_table")
    fun getAll(): Flow<List<Fav>>
    @Query("SELECT * FROM fav_table WHERE id=:id ")
    fun loadSingle(id: String): Flow<Fav>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(fav: Fav)

    @Query("DELETE FROM fav_table")
    fun deleteAll()

    @Delete
    fun delete(model: Fav)

    @Update
    suspend fun update(fav: Fav)
}