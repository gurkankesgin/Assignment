package com.turkcell.assignment.data.network

import com.turkcell.assignment.data.network.reponse.ProductListResponse
import com.turkcell.assignment.data.network.reponse.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiManager {

    @GET("list")
    suspend fun getProducts(): ProductListResponse

    @GET("{productId}/detail")
    suspend fun getProductById(@Path("productId") productId: String): ProductResponse

}
