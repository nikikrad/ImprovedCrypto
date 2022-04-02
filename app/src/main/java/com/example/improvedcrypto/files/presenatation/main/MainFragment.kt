package com.example.improvedcrypto.files.presenatation.main

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.*
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.popup.CustomDialogFragment
import com.example.improvedcrypto.files.presenatation.main.repository.MainRepository
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject


class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    private val mainViewModel: MainViewModel by inject()
    var responseBody: MutableList<CoinResponse> = emptyList<CoinResponse>().toMutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val bundle = Bundle()
//        bundle.getSerializable("COIN")

//        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
//        })

//        val array: ArrayList<CoinResponse> = bundle.getSerializable("COIN") as ArrayList<CoinResponse>

//        val array: ArrayList<CoinResponse> = bundle.getParcelableArrayList<Parcelable>("COIN") as ArrayList<CoinResponse>

//        val Adapter = MainAdapter(array)
//        binding.rvCoins.layoutManager =
//            LinearLayoutManager(
//                activity?.applicationContext,
//                LinearLayoutManager.VERTICAL,
//                false
//            )
//        binding.rvCoins.adapter = Adapter

//        Log.e("KEK", array.toString())

        responseBody.clear()
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            val Adapter = MainAdapter(it)
            binding.rvCoins.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
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