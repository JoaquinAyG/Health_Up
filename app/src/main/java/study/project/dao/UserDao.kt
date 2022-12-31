package study.project.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import study.project.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao : IDao<User>{
    @Query("SELECT * FROM user_table ORDER BY id ASC")
    override fun getAll(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun insert(model: User)

    @Query("DELETE FROM user_table")
    override fun deleteAll()

    @Delete
    override fun delete(model: User)

    @Update
    override suspend fun update(model: User)
}