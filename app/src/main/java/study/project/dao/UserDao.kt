package study.project.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import study.project.models.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)

    @Query("DELETE FROM user_table")
    fun deleteAll()

    @Delete
    fun delete(model: User)

    @Update
    suspend fun update(model: User)
}