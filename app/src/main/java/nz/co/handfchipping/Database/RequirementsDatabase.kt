package nz.co.handfchipping.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import nz.co.handfchipping.Models.Category
import nz.co.handfchipping.Models.Project
import nz.co.handfchipping.Models.Subcategory
import nz.co.handfchipping.Models.Task
import nz.co.handfchipping.Extensions.MyTypeConverters


@Database(entities = [Category::class, Subcategory::class, Task::class, Project::class], version = 8)
@TypeConverters(MyTypeConverters::class)
abstract class RequirementsDatabase: RoomDatabase() {
    abstract fun subcategoryDao() : SubcategoryDao
    abstract fun categoryDao(): CategoryDao
    abstract fun taskDao() : TaskDao
    abstract fun proejctDao() : ProjectDao

    companion object {
        @Volatile
        private var INSTANCE: RequirementsDatabase? = null

        fun getDatabase(context: Context): RequirementsDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RequirementsDatabase::class.java,
                    "Word_database"
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}