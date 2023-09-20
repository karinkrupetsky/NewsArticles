package com.example.randomjokegeneratormodified.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {

    //Here put the Repositories

    single { MainRepository(get()) }

    //Here put the ViewModels
    viewModelOf(::MainViewModel)

}