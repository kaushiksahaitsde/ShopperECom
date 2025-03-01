package com.example.shopper.di

import com.example.shopper.ui.feature.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule= module {
   viewModel {
       HomeViewModel(get())
   }
}