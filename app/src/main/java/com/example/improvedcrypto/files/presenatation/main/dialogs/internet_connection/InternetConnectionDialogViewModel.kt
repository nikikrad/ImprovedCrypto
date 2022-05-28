package com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.improvedcrypto.files.domain.ApiService
import com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection.repository.InternetConnectionRepository
import kotlinx.coroutines.launch
import retrofit2.Retrofit

class InternetConnectionDialogViewModel(private val internetConnectionRepository: InternetConnectionRepository) : ViewModel() {

    val liveData: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    fun getResponse(retrofit: ApiService) {

        viewModelScope.launch {
            try {
                var bodyGecon = internetConnectionRepository.getCoin(retrofit)
                if (bodyGecon != null)
                    liveData.postValue(true)
            } catch (e: Exception) {
                liveData.postValue(false)
            }
        }
    }
}