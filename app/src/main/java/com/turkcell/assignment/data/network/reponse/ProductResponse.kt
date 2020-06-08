package com.turkcell.assignment.data.network.reponse

import androidx.annotation.NonNull

data class ProductResponse(
    val product_id: String,
    val name: String?,
    val price: Int?,
    val image: String?,
    val description: String?
)
