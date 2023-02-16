package study.project.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import study.project.dao.UserDao
import study.project.models.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): UserRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "user_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(UserDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class UserDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.userDao())
                }
            }
        }
        suspend fun populateDatabase(userDao: UserDao) {
            userDao.deleteAll()
            val user = User(1,"a","b","a@a.a", 1.1, 1, "male", "1,2,3", 5, false)
            userDao.insert(user)
        }
    }
}