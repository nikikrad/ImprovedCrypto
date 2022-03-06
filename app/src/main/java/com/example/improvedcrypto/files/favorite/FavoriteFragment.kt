package com.example.improvedcrypto.files.favorite

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.improvedcrypto.databinding.FragmentFavoriteBinding
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.example.improvedcrypto.files.main.description.DescriptionCoinAdapter
import com.example.improvedcrypto.files.main.description.DescriptionCoinViewModel
import kotlinx.coroutines.launch


class FavoriteFragment : Fragment() {

    lateinit var binding: FragmentFavoriteBinding
    lateinit var favoriteViewModel: FavoriteViewModel
    lateinit var readAllData: LiveData<List<Coin>>
    var coinList = emptyList<Coin>()

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

        Thread.sleep(2000)




//        Log.e("TAG", )
//        val Adapter = FavoriteAdapter(readAllData)
//        binding.rvCoins.layoutManager =
//            LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
//        binding.rvCoins.adapter = Adapter
    }

    fun getAllData(): LiveData<List<Coin>> {
        val coinDao = activity?.let { CoinDatabase.getDatabase(it).CoinDao() }
        val repository: CoinRepository? = coinDao?.let { CoinRepository(it) }
        readAllData = repository?.readAllData!!


        return readAllData
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}