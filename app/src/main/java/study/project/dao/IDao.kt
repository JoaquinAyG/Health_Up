package study.project.dao

import kotlinx.coroutines.flow.Flow

interface IDao<T> {
    suspend fun insert(model: T)
    fun delete(model: T)
    fun deleteAll()
    fun getAll(): Flow<List<T>>
    suspend fun update(model: T)
}
