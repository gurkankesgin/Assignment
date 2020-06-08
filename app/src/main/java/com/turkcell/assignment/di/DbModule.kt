package com.turkcell.assignment.di

import android.content.Context
import androidx.room.Room
import com.turkcell.assignment.data.db.DbManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): DbManager {
        return  Room.databaseBuilder(context.applicationContext, DbManager::class.java, "RoomDatabase.db").build()
    }
}