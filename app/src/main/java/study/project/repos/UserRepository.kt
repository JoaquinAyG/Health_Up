package study.project.repos

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import study.project.dao.UserDao
import study.project.models.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: Flow<List<User>> = userDao.getAll()
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insert(user)
    }
    suspend fun deleteAll() {
        userDao.deleteAll()
    }
    suspend fun delete(user: User) {
        userDao.delete(user)
    }
    suspend fun update(user: User) {
        userDao.update(user)
    }
    suspend fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }
}