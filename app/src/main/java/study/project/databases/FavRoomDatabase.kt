package study.project.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import study.project.dao.FavDao
import study.project.models.Fav

@Database(entities = [Fav::class], version = 1, exportSchema = false)
abstract class FavRoomDatabase : RoomDatabase() {

    abstract fun favDao(): FavDao

    companion object {

        @Volatile
        private var INSTANCE: FavRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FavRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FavRoomDatabase::class.java,
                    "fav_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(FavDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class FavDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                }
            }
        }
    }
}