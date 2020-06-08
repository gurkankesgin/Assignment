package com.turkcell.assignment.presentation.list

import com.turkcell.assignment.base.BaseViewModel
import com.turkcell.assignment.data.repositories.ProductRepository
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class ProductListViewModel(repository: ProductRepository) : BaseViewModel() {

    val products by lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            repository.getProducts()
        }
    }
}