package com.example.improvedcrypto.files.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Database
import com.example.improvedcrypto.databinding.FragmentFavoriteBinding
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.example.improvedcrypto.files.main.description.DescriptionCoinAdapter
import com.example.improvedcrypto.files.main.description.DescriptionCoinViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteViewModel: FavoriteViewModel

    //    lateinit var readAllData: List<Coin>

    var coinList:  MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
//        arguments?.getString("ID")?.let { favoriteViewModel.getDescriptionResponse(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        getAllData()

//        Thread.sleep(2000)


//        Log.e("TAG", readAllData.toString())

        Thread.sleep(100)
        val Adapter = FavoriteAdapter(coinList)
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.rvCoins.adapter = Adapter
    }

    fun getAllData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val coinDao = activity?.let { CoinDatabase.getDatabase(it).CoinDao() }
            val repository: CoinRepository? = coinDao?.let { CoinRepository(it) }
            val readAllData = repository?.readAllData
            if (readAllData != null) {
                readAllData.forEach{
                    coinList.add(DatabaseParameters(it.symbol, it.name, it.image, it.description, it.currentPrice, it.changePrice))
                }
            }
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}