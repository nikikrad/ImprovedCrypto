package com.example.improvedcrypto.files.presenatation.main.dialogs.internet_connection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.DialogInternetConnectionBinding

class InternetConnectionDialogFragment: DialogFragment(){

    private lateinit var binding: DialogInternetConnectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_internet_connection, container, false)
        binding = DialogInternetConnectionBinding.inflate(inflater, container, false)

        rootView.findViewById<View>(R.id.btn_Retry).setOnClickListener {
            dismissDialog()
        }
        return rootView
    }

    fun dismissDialog() {
        try{
            dismiss()
        }catch (e: Exception){
            Log.e("Error: ", e.toString())
        }
    }
}