package com.turkcell.assignment

import com.turkcell.assignment.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this)
        return appComponent
    }

}