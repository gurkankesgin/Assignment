package com.turkcell.assignment.di

import com.turkcell.assignment.presentation.MainActivity
import com.turkcell.assignment.presentation.MainActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun buildMainActivity(): MainActivity
}