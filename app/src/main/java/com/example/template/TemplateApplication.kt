package com.example.template

import android.app.Application
import com.example.randomjokegeneratormodified.di.appModule
import com.example.template.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TemplateApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TemplateApplication)
            modules(appModule, networkModule)
        }
    }
}