package study.project

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import study.project.databases.UserRoomDatabase
import study.project.repos.UserRepository

class HealthUpApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { UserRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}