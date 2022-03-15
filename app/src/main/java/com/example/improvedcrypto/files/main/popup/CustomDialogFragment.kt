package com.example.improvedcrypto.files.main.popup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentDialogBinding

class CustomDialogFragment: DialogFragment() {

    lateinit var binding: FragmentDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView: View = inflater.inflate(R.layout.fragment_dialog, container, false)
        binding = FragmentDialogBinding.inflate(inflater, container, false)

        binding.btnSortByPrice.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "By Price!", Toast.LENGTH_SHORT).show()
        }

        binding.btnSortAlphabetically.setOnClickListener {
            Toast.makeText(activity?.applicationContext, "Alpha!", Toast.LENGTH_SHORT).show()
        }



        return rootView
    }
}