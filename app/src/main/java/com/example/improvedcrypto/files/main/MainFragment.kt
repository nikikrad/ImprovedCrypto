package com.example.improvedcrypto.files.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.main.dataclass.CoinResponse

class MainFragment: Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mainViewModel: MainViewModel
    var responseBody: MutableList<CoinResponse> = emptyList<CoinResponse>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.getResponse()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            it.forEach {
                responseBody.add(it)
            }
        })

        val Adapter = MainAdapter(responseBody)
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvCoins.adapter = Adapter
        responseBody.clear()

    }

    override fun onDestroy() {
        super.onDestroy()
    }
}