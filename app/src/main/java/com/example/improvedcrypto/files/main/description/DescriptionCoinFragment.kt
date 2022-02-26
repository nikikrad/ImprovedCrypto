package com.example.improvedcrypto.files.main.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding
import com.example.improvedcrypto.files.repository.TemporaryId

class DescriptionCoinFragment: Fragment() {

    lateinit var binding: FragmentDescriptionBinding
    var temporaryId = TemporaryId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        Log.e("IDISHNIK", temporaryId.id)

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}