package com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.DialogInternetConnectionBinding
import com.example.improvedcrypto.files.domain.ApiService
import org.koin.android.ext.android.inject

class InternetConnectionDialogFragment: DialogFragment(){

    private lateinit var binding: DialogInternetConnectionBinding
    private val internetConnectionDialogViewModel: InternetConnectionDialogViewModel by inject()
    private val retrofit: ApiService by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_internet_connection, container, false)
        binding = DialogInternetConnectionBinding.inflate(inflater, container, false)

        rootView.findViewById<View>(R.id.btn_Retry).setOnClickListener {
            internetConnectionDialogViewModel.getResponse(retrofit)
            internetConnectionDialogViewModel.liveData.observe(viewLifecycleOwner, Observer{
                if(it == true){
                    dismiss()
                }
            })
        }

        return rootView
    }
}