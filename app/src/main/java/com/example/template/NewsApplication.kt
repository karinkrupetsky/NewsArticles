package com.example.template

import android.app.Application
import com.example.template.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class NewsApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApplication)
            modules(appModule)

        }
    }
}