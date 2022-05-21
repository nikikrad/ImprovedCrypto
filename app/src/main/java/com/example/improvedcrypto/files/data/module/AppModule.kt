package com.example.improvedcrypto.files.data.module

import android.app.Application
import androidx.room.Room
import com.example.improvedcrypto.files.data.CoinDao
import com.example.improvedcrypto.files.data.CoinDatabase
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

    single <MainRepository> { MainRepository() }

    viewModel { MainViewModel(get()) }

    single <DescriptionRepository> { DescriptionRepository() }

    viewModel { DescriptionCoinViewModel(get()) }

    single { FavoriteRepository(get()) }

    viewModel { FavoriteViewModel(get(), get()) }

    single { InternetConnectionRepository() }

    viewModel { InternetConnectionDialogViewModel(get()) }

    ///////////////////////////////////////////////

    fun CoinDatabase(application: Application): CoinDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            CoinDatabase::class.java,
            "coin_database"
        ).build()
    }

    fun CoinDao(coinDatabase: CoinDatabase): CoinDao {
        return coinDatabase.CoinDao()
    }

    single { CoinDatabase(androidApplication()) }
    single { CoinDao(get()) }

}

//val Database = module {
//    fun provideDataBase(application: Application): CoinDatabase {
//        return Room.databaseBuilder(application, CoinDatabase::class.java, "USERDB")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    fun provideDao(coinDatabase: CoinDatabase): CoinDao {
//        return coinDatabase.CoinDao()
//    }
//    single { provideDataBase(androidApplication()) }
//    single { provideDao(get()) }
//
//}