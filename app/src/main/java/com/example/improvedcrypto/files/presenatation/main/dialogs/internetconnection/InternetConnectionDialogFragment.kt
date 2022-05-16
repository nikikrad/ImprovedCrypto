package com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.DialogInternetConnectionBinding
import com.example.improvedcrypto.databinding.FragmentDialogBinding
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class InternetConnectionDialogFragment: DialogFragment(){

    lateinit var binding: DialogInternetConnectionBinding
    val internetConnectionDialogViewModel: InternetConnectionDialogViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView: View = inflater.inflate(R.layout.dialog_internet_connection, container, false)
        binding = DialogInternetConnectionBinding.inflate(inflater, container, false)

        rootView.findViewById<View>(R.id.btn_Retry).setOnClickListener {
            internetConnectionDialogViewModel.getResponse()
            internetConnectionDialogViewModel.liveData.observe(viewLifecycleOwner, Observer{
                if(it == true){
                    dismiss()
                }else{

                }
            })
        }

        return rootView
    }
}