package study.project.repos

import androidx.annotation.WorkerThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import study.project.dao.FavDao
import study.project.models.Fav
import study.project.models.User

class FavRepository(private val favDao: FavDao) {

    val allFavs: Flow<List<Fav>> = favDao.getAll()

    @WorkerThread
    fun loadSingle(id: String): Flow<Fav> {
        return favDao.loadSingle(id)
    }
    @WorkerThread
    suspend fun insert(fav: Fav) {
        favDao.insert(fav)
    }
    @WorkerThread
    fun deleteAll() {
        favDao.deleteAll()
    }
    @WorkerThread
    fun delete(fav: Fav) {
        favDao.delete(fav)
    }
    @WorkerThread
    suspend fun update(fav: Fav) {
        favDao.update(fav)
    }
}