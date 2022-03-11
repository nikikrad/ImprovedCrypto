package com.example.improvedcrypto.files.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.improvedcrypto.R
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

        val Adapter = activity?.let { FavoriteAdapter(getAllData(), binding, favoriteViewModel, it.applicationContext ) }
        binding.rvCoins.layoutManager =
            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCoins.adapter = Adapter

        refreshApp()
    }

    fun getAllData(): MutableList<DatabaseParameters>  {
        var coinList: MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { getDatabase(it) }
            coinList = favoriteViewModel.getAllData(database)
        }
        Thread.sleep(100)
        return coinList
    }

    fun showSnackBar(
        binding: FragmentFavoriteBinding,
        coin: DatabaseParameters,
        favoriteViewModel: FavoriteViewModel,
        applicationContext: Context
    ) {
        Snackbar.make(
            binding.fragmentFavoriteCoin,
            "You want to delete " + coin.name + " coin?",
            Snackbar.LENGTH_LONG
        ).setAction("Delete") {
            val processedCoin = favoriteViewModel.processingCoin(coin)
            deleteCoin(processedCoin, favoriteViewModel, applicationContext)
            lifecycleScope.launchWhenResumed {
               val navHostFragment =
                    parentFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
                navHostFragment.findNavController().navigate(R.id.action_favoriteFragment_self)
            }
            Toast.makeText(applicationContext, "Removal is Successful", Toast.LENGTH_SHORT).show()
        }.show()
    }

    fun getDatabase(context: Context): CoinDatabase {
            val database = context.let { CoinDatabase.getDatabase(it) }
        return database
    }

    fun deleteCoin(coin: Coin, favoriteViewModel: FavoriteViewModel, applicationContext: Context) {
        lifecycleScope.launch(Dispatchers.IO) {
            val database = getDatabase(applicationContext)
            favoriteViewModel.deleteCoin(coin, database)
        }
    }

    private fun refreshApp(){
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            NavHostFragment.findNavController(this).navigate(R.id.action_favoriteFragment_self)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }
}