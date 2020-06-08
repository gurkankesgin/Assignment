package com.turkcell.assignment.data.mapper

import com.turkcell.assignment.data.db.entities.Product
import com.turkcell.assignment.data.network.reponse.ProductResponse
import dagger.Provides
import dagger.Reusable
import javax.inject.Inject
import javax.inject.Singleton

interface ProductMapper {
    fun mapProduct(productResponse: ProductResponse): Product
}

@Singleton
class ProductMapperImpl @Inject constructor() : ProductMapper {
    override fun mapProduct(productResponse: ProductResponse): Product =
        Product(
            productResponse.product_id,
            productResponse.name,
            productResponse.price,
            productResponse.image,
            productResponse.description
        )
}