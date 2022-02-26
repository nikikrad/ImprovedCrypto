//package com.example.improvedcrypto.files.main.description.factory
//
//import android.app.Application
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.improvedcrypto.files.main.description.DescriptionCoinViewModel
//
//class DescriptionCoinFactory(val application: Application, val id: String) :
//    ViewModelProvider.AndroidViewModelFactory(application) {
//
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return DescriptionCoinViewModel(application, id) as T
//    }
//}