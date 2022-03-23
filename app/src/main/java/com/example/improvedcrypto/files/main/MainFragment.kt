package com.example.improvedcrypto.files.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import com.example.improvedcrypto.files.main.popup.CustomDialogFragment


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var mainViewModel: MainViewModel
    var responseBody: MutableList<CoinResponse> = emptyList<CoinResponse>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mainViewModel.getResponse()

        adapter()
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshApp()
        }
        binding.ibSort.setOnClickListener {
            val dialog = CustomDialogFragment()
            dialog.show(childFragmentManager, "qwe")
        }
    }

    private fun adapter() {

        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            responseBody.clear()
            val Adapter = MainAdapter(it)
            binding.rvCoins.layoutManager =
                LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            binding.rvCoins.adapter = Adapter
        })

    }

    private fun refreshApp() {
        mainViewModel.getResponse()
        binding.swipeRefreshLayout.isRefreshing = false
        adapter()
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}