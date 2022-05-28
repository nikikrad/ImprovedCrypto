package com.example.improvedcrypto.files.data.module

import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.domain.instance.RetrofitInstance
import com.example.improvedcrypto.files.presenatation.favorite.FavoriteViewModel
import com.example.improvedcrypto.files.presenatation.favorite.repository.FavoriteRepository
import com.example.improvedcrypto.files.presenatation.main.MainViewModel
import com.example.improvedcrypto.files.presenatation.main.description.DescriptionCoinViewModel
import com.example.improvedcrypto.files.presenatation.main.description.repository.DescriptionRepository
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.InternetConnectionDialogViewModel
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.repository.InternetConnectionRepository
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { MainRepository(get()) }

    viewModel { MainViewModel(get()) }

    single { DescriptionRepository(get(), get()) }

    viewModel { DescriptionCoinViewModel(get()) }

    single { FavoriteRepository(get()) }

    viewModel { FavoriteViewModel(get()) }

    single { InternetConnectionRepository() }

    viewModel { InternetConnectionDialogViewModel(get()) }
}

val retrofitModule = module{

    fun provideRetrofit(): ApiService {
        return RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
    }

    single { provideRetrofit() }
}

val roomModule = module{

    single { CoinEntity() }

    fun provideCoinDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.CoinDao()
    }

    single { provideCoinDao(get()) }

    single { CoinDatabase.getDatabase(androidApplication()) }
}