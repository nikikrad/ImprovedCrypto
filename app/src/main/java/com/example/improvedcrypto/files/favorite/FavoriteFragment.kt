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
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteViewModel: FavoriteViewModel

//    var coinList: MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()


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

        val Adapter = FavoriteAdapter(getAllData(), binding, favoriteViewModel)
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCoins.adapter = Adapter


    }

    fun getAllData(): MutableList<DatabaseParameters>  {
        var coinList: MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { CoinDatabase.getDatabase(it) }
            coinList = favoriteViewModel.getAllData(database)
        }
        Thread.sleep(100)
        return coinList
    }

    fun showSnackBar(
        binding: FragmentFavoriteBinding,
        coin: DatabaseParameters,
        favoriteViewModel: FavoriteViewModel
    ) {
        Snackbar.make(
            binding.fragmentFavoriteCoin,
            "You want to delete " + coin.name + " coin?",
            Snackbar.LENGTH_LONG
        ).setAction("Delete") {
            val processedCoin = favoriteViewModel.processingCoin(coin)
            deleteCoin(processedCoin, favoriteViewModel)
        }.show()
    }

    fun deleteCoin(coin: Coin, favoriteViewModel: FavoriteViewModel) {
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { CoinDatabase.getDatabase(it) }
            favoriteViewModel.deleteCoin(coin, database)
        }


    }

    override fun onDestroy() {
        super.onDestroy()
    }
}