package study.project.repos

import android.util.Log
import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import study.project.dao.UserDao
import study.project.models.Exercise
import study.project.models.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAll()
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
    @WorkerThread
    fun deleteAll() {
        userDao.deleteAll()
    }
    @WorkerThread
    fun delete(user: User) {
        userDao.delete(user)
    }
    @WorkerThread
    suspend fun update(user: User) {
        userDao.update(user)
    }
    @WorkerThread
    suspend fun updateFavoriteExercise(instance: User, exercise: Exercise) {
        instance.addFavoriteExercise(exercise.id)
        userDao.update(instance)
    }
}