package com.example.improvedcrypto.files.main

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainFragment : Fragment() {

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

        adapter()
        refreshApp()
    }

    private fun getAllCoinFromServer(): MutableList<CoinResponse>{
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            it.forEach {
                responseBody.add(it)
            }
        })
        return responseBody
    }


    private fun adapter(){
        responseBody.clear()
        val Adapter = MainAdapter(getAllCoinFromServer())
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvCoins.adapter = Adapter
    }


    private fun refreshApp(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false

            adapter()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}