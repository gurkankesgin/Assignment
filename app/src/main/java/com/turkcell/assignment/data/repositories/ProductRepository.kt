package com.turkcell.assignment.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.turkcell.assignment.data.db.DbManager
import com.turkcell.assignment.data.db.entities.Product
import com.turkcell.assignment.data.mapper.ProductMapperImpl
import com.turkcell.assignment.data.network.ApiManager
import com.turkcell.assignment.data.network.networkutils.Resource
import com.turkcell.assignment.data.network.networkutils.ResponseHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductRepository(
    private val api: ApiManager,
    private val db: DbManager,
    private val responseHandler: ResponseHandler,
    private val productMapperImpl: ProductMapperImpl
) {

    /**
     *      product listesi sadece livedataya bağlı offline dbden beslenir.
     *      online data geldiğinde dbye insert edildiği için tekrar db tetiklenir ve ui update olur
     *
     *      product detayında ise sadece retrofitin online cache i mevcut.
     */
    private val productList = MutableLiveData<List<Product>>()

    init {
        productList.observeForever { saveProducts(it) }
    }

    suspend fun getProducts(): LiveData<List<Product>> = withContext(Dispatchers.IO) {
        fetchProducts()
        db.getProductDao().getProducts()
    }

    suspend fun getProductById(id: String): Resource<Product> = fetchProductById(id)

    private suspend fun fetchProducts() {
        try {
            val response = api.getProducts()
            val productList = response.products.map { productMapperImpl.mapProduct(it) }
            this.productList.postValue(productList)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private suspend fun fetchProductById(id: String): Resource<Product> {
        return try {
            val response = api.getProductById(id)
            val product = productMapperImpl.mapProduct(response)
            responseHandler.handleSuccess(product)
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }

    private fun saveProducts(it: List<Product>) {
        CoroutineScope(Dispatchers.IO).launch {
            db.getProductDao().saveProducts(it)
        }
    }
}