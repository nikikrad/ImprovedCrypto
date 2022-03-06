package com.example.improvedcrypto.files.main.description

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import android.content.Context as Context

class DescriptionCoinFragment : Fragment() {


    lateinit var binding: FragmentDescriptionBinding
    lateinit var descriotionCoinViewModel: DescriptionCoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        descriotionCoinViewModel = ViewModelProvider(this)[DescriptionCoinViewModel::class.java]
        arguments?.getString("ID")?.let { descriotionCoinViewModel.getDescriptionResponse(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)

//        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        descriotionCoinViewModel.liveData.observe(viewLifecycleOwner, Observer {

            Glide.with(view)
                .load(it.image.large)
                .placeholder(R.drawable.ic_no_image)
                .into(binding.ivAvatar)

            binding.tvName.setText(it.name)

            binding.tvSymbol.setText(it.symbol)

            binding.tvChangePrice.setText(it.marketData.changePrice.toString() + " %")

            var resource: Resources = resources
            var textRedColor = resource.getColor(R.color.red, null)
            var textGreenColor = resource.getColor(R.color.green, null)
            if (it.marketData.changePrice > 0) binding.tvChangePrice.setTextColor(textGreenColor)
            else binding.tvChangePrice.setTextColor(textRedColor)

            binding.tvPrice.setText(it.marketData.currentPrice.usd.toString() + " $")

            val Adapter = DescriptionCoinAdapter(it.description.en)
            binding.rvDescription.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            binding.rvDescription.adapter = Adapter

            val coin = Coin(
                0,
                it.symbol,
                it.name,
                it.image.large,
                it.description.en,
                it.marketData.currentPrice.usd,
                it.marketData.changePrice
            )

            binding.btnAddToDataBase.setOnClickListener {
                insertToDataBase(coin)
            }
        })

    }


    fun insertToDataBase(coin: Coin) {
        lifecycleScope.launch(Dispatchers.IO) {
            val coinDao = activity?.let { CoinDatabase.getDatabase(it).CoinDao() }
            val repository: CoinRepository? = coinDao?.let { CoinRepository(it) }
            if (repository != null) {
                repository.addCoin(coin)
            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//
//        super.onCreateOptionsMenu(menu, inflater)
//    }

    override fun onDestroy() {
        super.onDestroy()
    }
}