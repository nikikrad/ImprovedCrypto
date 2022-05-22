package com.example.improvedcrypto.files.presenatation.favorite

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentFavoriteBinding
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter(binding)
        refreshApp(binding)
    }


    private fun getAllData(): MutableList<CoinItem> {
        var coinList: MutableList<CoinItem> =
            emptyList<CoinItem>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            coinList = favoriteViewModel.getAllData()
        }
        Thread.sleep(100)
        return coinList
    }

    private fun adapter(binding: FragmentFavoriteBinding) {
        val isEmpty = getAllData()
        if (isEmpty.size == 0) {
            binding.tvNoCoin.visibility = View.VISIBLE
        } else {
            binding.tvNoCoin.visibility = View.INVISIBLE
        }
        val adapter = activity?.let {
            FavoriteAdapter(
                getAllData(),
                binding,
                favoriteViewModel,
                it.applicationContext
            )
        }
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true)
        linearLayoutManager.reverseLayout = true
        linearLayoutManager.stackFromEnd = true
        binding.rvCoins.layoutManager = linearLayoutManager
        binding.rvCoins.adapter = adapter
    }

    fun showSnackBar(
        binding: FragmentFavoriteBinding,
        coin: CoinItem,
        favoriteViewModel: FavoriteViewModel,
        applicationContext: Context
    ) {
        Snackbar.make(
            binding.fragmentFavoriteCoin,
            "Do you want to delete " + coin.name + " coin?",
            Snackbar.LENGTH_LONG
        ).setAction("Delete") {
            val processedCoin = favoriteViewModel.processingCoin(coin)
            deleteCoin(processedCoin, favoriteViewModel)
            Toast.makeText(applicationContext, "Removal is Successful", Toast.LENGTH_SHORT).show()
        }.show()
    }

    private fun deleteCoin(
        coinEntity: CoinEntity,
        favoriteViewModel: FavoriteViewModel,
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            favoriteViewModel.deleteCoin(coinEntity)
        }
    }

    private fun refreshApp(binding: FragmentFavoriteBinding) {
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            adapter(binding)
        }
    }
}