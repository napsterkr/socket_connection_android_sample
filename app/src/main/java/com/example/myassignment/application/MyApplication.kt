package com.example.myassignment.application

import android.app.Application
import com.example.myassignment.di.CoinModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin { androidContext(this@MyApplication) }

        loadKoinModules(viewModelModule)
    }
}