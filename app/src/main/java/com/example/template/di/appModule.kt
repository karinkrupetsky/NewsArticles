package com.example.template.di


import LocalDataSource
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.template.features.MainViewModel
import com.example.template.repositories.NewsRepository


val appModule = module {

    //Here put the Repositories

    single { NewsRepository(get()) }
    single { LocalDataSource(get()) }

    //Here put the ViewModels
    viewModelOf(::MainViewModel)

}