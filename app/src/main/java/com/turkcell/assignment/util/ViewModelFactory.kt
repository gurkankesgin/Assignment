package com.turkcell.assignment.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.turkcell.assignment.data.repositories.ProductRepository
import com.turkcell.assignment.presentation.detail.ProductDetailViewModel
import com.turkcell.assignment.presentation.list.ProductListViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelProviderFactory @Inject constructor(var repository: ProductRepository) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ProductDetailViewModel::class.java) -> ProductDetailViewModel(repository) as T
            modelClass.isAssignableFrom(ProductListViewModel::class.java) -> ProductListViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name) as Throwable
        }
    }
}