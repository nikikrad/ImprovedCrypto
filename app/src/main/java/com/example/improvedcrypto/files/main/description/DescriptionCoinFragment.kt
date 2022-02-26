package com.example.improvedcrypto.files.main.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding

class DescriptionCoinFragment : Fragment() {

    lateinit var binding: FragmentDescriptionBinding
    lateinit var descriotionCoinViewModel: DescriptionCoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        descriotionCoinViewModel = ViewModelProvider(this)[DescriptionCoinViewModel::class.java]
        arguments?.getString("ID")?.let { descriotionCoinViewModel.getCoinId(it) }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val id = arguments?.getString("ID")
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}