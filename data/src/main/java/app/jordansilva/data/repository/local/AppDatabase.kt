package app.jordansilva.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import app.jordansilva.data.model.RecipeModel
import app.jordansilva.data.model.UserModel
import app.jordansilva.data.repository.local.converter.DateConverter
import app.jordansilva.data.repository.local.converter.RecipeIngredientConverter

@Database(entities = [(UserModel::class), (RecipeModel::class)],
        version = AppDatabase.VERSION)
@TypeConverters(DateConverter::class, RecipeIngredientConverter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun userDao(): UserDao
    abstract fun recipeDao(): RecipesDao

    companion object {

        const val VERSION = 1

        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, "database.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}