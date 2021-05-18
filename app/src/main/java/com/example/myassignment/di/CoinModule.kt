package com.example.myassignment.di

import com.example.myassignment.viewModel.HomeListActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

object CoinModule {


    val viewModelModule = module {
        viewModel { HomeListActivityViewModel() }

    }


}