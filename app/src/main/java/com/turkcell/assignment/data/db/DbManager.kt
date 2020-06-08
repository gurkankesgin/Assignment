package com.turkcell.assignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.turkcell.assignment.data.db.dao.ProductDao
import com.turkcell.assignment.data.db.entities.Product

@Database(entities = [Product::class], version = 1)
abstract class DbManager : RoomDatabase() {

    abstract fun getProductDao(): ProductDao
}