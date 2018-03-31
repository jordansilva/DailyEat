package com.jordansilva.dailyeat.data.repository.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.jordansilva.dailyeat.data.model.Recipe
import com.jordansilva.dailyeat.data.model.User
import com.jordansilva.dailyeat.data.repository.DataConverter

@Database(entities = arrayOf(User::class, Recipe::class), version = AppDatabase.VERSION)
@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {


    abstract fun userDao(): UsersDao
    abstract fun recipeDao(): RecipesDao

    companion object {

        const val VERSION = 1

        private var INSTANCE: AppDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java,
                            "database.db").build()
                }
                return INSTANCE!!
            }
        }
    }
}