package com.example.improvedcrypto.files.presenatation.main

import android.os.Bundle
import android.os.Parcelable
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentMainBinding
import com.example.improvedcrypto.files.data.dataclass.CoinResponse
import com.example.improvedcrypto.files.presenatation.main.dialogs.filter.CustomDialogFragment
import com.example.improvedcrypto.files.presenatation.main.dialogs.internetconnection.InternetConnectionDialogFragment
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
//        noInternetConnection()
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

        var bundle = Bundle()
        var nikita = bundle.getParcelableArrayList<Parcelable>("COIN")
        var oleg = arguments?.getParcelableArrayList<Parcelable>("COIN")

         responseBody.clear()
            mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
                binding.pbProgressBar.isVisible = it.isEmpty()
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

    fun noInternetConnection(){
        val dialogFragment = InternetConnectionDialogFragment()
        dialogFragment.show(childFragmentManager, "Hello")
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}