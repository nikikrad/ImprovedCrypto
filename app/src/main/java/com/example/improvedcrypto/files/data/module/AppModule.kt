package com.example.improvedcrypto.files.data.module

import com.example.improvedcrypto.files.presenatation.main.MainViewModel
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single <MainRepository> { MainRepository() }

    viewModel { MainViewModel(get()) }


}