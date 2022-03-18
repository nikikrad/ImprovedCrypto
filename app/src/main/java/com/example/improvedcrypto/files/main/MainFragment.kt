package com.example.improvedcrypto.files.main

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
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
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.ResponseCoinEntity
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.main.popup.CustomDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


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
        binding.ibSort.setOnClickListener {
            val dialog = CustomDialogFragment()
            dialog.show(childFragmentManager, "qwe")
        }
    }

    private fun getAllCoinFromServer(): MutableList<CoinResponse> {
        val responseCoinEntity: MutableList<ResponseCoinEntity> = emptyList<ResponseCoinEntity>().toMutableList()
        mainViewModel.liveData.observe(viewLifecycleOwner, Observer {
            it.forEach {
                responseBody.add(it)
                responseCoinEntity.add(
                    ResponseCoinEntity(
                        0,
                        it.id,
                        it.name,
                        it.symbol,
                        it.image,
                        it.price
                    )
                )
            }
            addCoinsToDataBase(responseCoinEntity)
        })
        return responseBody
    }

    private fun adapter() {
        responseBody.clear()
        val Adapter = MainAdapter(getAllData())
        val mLayoutManager = LinearLayoutManager(context);
        mLayoutManager.setReverseLayout(true)
        mLayoutManager.stackFromEnd
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvCoins.adapter = Adapter
    }

    fun addCoinsToDataBase(coinList: MutableList<ResponseCoinEntity>) {
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { CoinDatabase.getDatabase(it) }
            if (database != null) {
                mainViewModel.sendResponseCoinToDatabase(coinList, database)
            }
        }
    }

    private fun refreshApp() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter()
        }
    }

    fun getDatabase(context: Context): CoinDatabase {
        val database = context.let { CoinDatabase.getDatabase(it) }
        return database
    }

    fun getAllData(): List<CoinResponse> {
        var coinList: List<CoinResponse> =
            emptyList<CoinResponse>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { getDatabase(it) }
            coinList = mainViewModel.getAllData(database)
        }
        Thread.sleep(100)
        return coinList
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}