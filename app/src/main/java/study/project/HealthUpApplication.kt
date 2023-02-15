package study.project

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import study.project.databases.FavRoomDatabase
import study.project.databases.UserRoomDatabase
import study.project.repos.FavRepository
import study.project.repos.UserRepository

class HealthUpApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val userDatabase by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
    val userRepository by lazy { UserRepository(userDatabase.userDao()) }
    val favDatabase by lazy { FavRoomDatabase.getDatabase(this, applicationScope) }
    val favRepository by lazy { FavRepository(favDatabase.favDao()) }
}