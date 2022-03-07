package com.example.improvedcrypto.files.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentFavoriteBinding
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteViewModel: FavoriteViewModel

    var coinList: MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
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

        Thread.sleep(100)
        val Adapter = FavoriteAdapter(coinList, binding, favoriteViewModel)
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCoins.adapter = Adapter


    }

    fun getAllData() {
        lifecycleScope.launch(Dispatchers.IO) {
            val coinDao = activity?.let { CoinDatabase.getDatabase(it).CoinDao() }
            val repository: CoinRepository? = coinDao?.let { CoinRepository(it) }
            val readAllData = repository?.readAllData
            if (readAllData != null) {
                readAllData.forEach {
                    coinList.add(
                        DatabaseParameters(
                            it.symbol,
                            it.name,
                            it.image,
                            it.description,
                            it.currentPrice,
                            it.changePrice
                        )
                    )
                }
            }
        }
    }

    fun showSnackBar(
        binding: FragmentFavoriteBinding,
        coin: DatabaseParameters,
        favoriteViewModel: FavoriteViewModel
    ){
        Snackbar.make(
            binding.fragmentFavoriteCoin,
            "You want to delete this Coin?",
            Snackbar.LENGTH_LONG
        ).setAction("Delete"){
            val processedCoin = favoriteViewModel.processingCoin(coin)
            deleteCoin(processedCoin)f
        }.show()

//        Toast.makeText(activity, "That is do", Toast.LENGTH_LONG).show()
    }

    fun deleteCoin(coin: Coin){
        lifecycleScope.launch(Dispatchers.Main) {
            val coinDao = activity?.let { CoinDatabase.getDatabase(it).CoinDao() }
            val repository: CoinRepository? = coinDao?.let { CoinRepository(it) }
            repository?.deleteCoin(coin)

        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}