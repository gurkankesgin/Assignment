package com.turkcell.assignment.presentation.detail

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.turkcell.assignment.base.BaseViewModel
import com.turkcell.assignment.data.db.entities.Product
import com.turkcell.assignment.data.repositories.ProductRepository
import com.turkcell.assignment.util.enums.ViewState
import kotlinx.coroutines.*

class ProductDetailViewModel(private val repository: ProductRepository) : BaseViewModel() {

    val product = MutableLiveData<Product>()
    val viewState = ObservableField<ViewState>(ViewState.LOADING)

    suspend fun getProduct(id: String) = withContext(Dispatchers.IO) {
        addDelay()
        repository.getProductById(id)
    }

    private suspend fun addDelay() = delay(500)
}