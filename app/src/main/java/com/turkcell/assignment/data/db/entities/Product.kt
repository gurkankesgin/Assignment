package com.turkcell.assignment.data.db.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Product(
    @PrimaryKey
    val product_id: String,
    val name: String?,
    val price: Int?,
    val image: String?,
    val description: String?
) : Parcelable