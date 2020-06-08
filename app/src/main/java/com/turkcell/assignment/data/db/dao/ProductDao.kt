package com.turkcell.assignment.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.turkcell.assignment.data.db.entities.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveProducts(quotes: List<Product>)

    @Query("SELECT * FROM Product")
    fun getProducts(): LiveData<List<Product>>
}