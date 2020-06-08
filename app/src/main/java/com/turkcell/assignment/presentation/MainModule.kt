package com.turkcell.assignment.presentation

import com.turkcell.assignment.presentation.detail.ProductDetailFragment
import com.turkcell.assignment.presentation.list.ProductListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun provideDetailFragment(): ProductDetailFragment

    @ContributesAndroidInjector
    abstract fun provideListFragment(): ProductListFragment
}