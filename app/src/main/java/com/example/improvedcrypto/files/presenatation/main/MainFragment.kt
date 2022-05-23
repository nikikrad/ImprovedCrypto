package com.example.improvedcrypto.files.presenatation.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.dialogs.filter.CustomDialogFragment
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.InternetConnectionDialogFragment
import org.koin.android.ext.android.inject


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by inject()
    private var responseBody: MutableList<CoinResponse> = emptyList<CoinResponse>().toMutableList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        mainViewModel.liveDataBoolean.observe(viewLifecycleOwner){
            if(it)
                noInternetConnection()
        }

        responseBody.clear()
        mainViewModel.liveData.observe(viewLifecycleOwner){
            binding.pbProgressBar.isVisible = it.isEmpty()
            val adapter = MainAdapter(it)
            binding.rvCoins.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            binding.rvCoins.adapter = adapter
        }
    }

    private fun refreshApp() {
        mainViewModel.getResponse()
        binding.swipeRefreshLayout.isRefreshing = false
        adapter()
    }

    private fun noInternetConnection() {
        val dialogFragment = InternetConnectionDialogFragment()
        dialogFragment.show(childFragmentManager, "Hello")
    }
}