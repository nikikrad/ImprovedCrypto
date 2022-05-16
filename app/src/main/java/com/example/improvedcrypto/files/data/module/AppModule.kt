package com.example.improvedcrypto.files.data.module

import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.presenatation.favorite.FavoriteViewModel
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.presenatation.main.MainViewModel
import com.example.improvedcrypto.files.presenatation.main.description.DescriptionCoinViewModel
import com.example.improvedcrypto.files.presenatation.main.description.repository.DescriptionRepository
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.InternetConnectionDialogViewModel
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single <MainRepository> { MainRepository() }

    viewModel { MainViewModel(get()) }

    single <DescriptionRepository> { DescriptionRepository() }

    viewModel { DescriptionCoinViewModel(get()) }
//
//    single { FavoriteRepository( as CoinDao) }
//
//    viewModel { FavoriteViewModel(get()) }

    viewModel { InternetConnectionDialogViewModel() }


}