package com.example.improvedcrypto.files.main.description

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.Switch
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            val resource: Resources = resources
            val textRedColor = resource.getColor(R.color.red, null)
            val textGreenColor = resource.getColor(R.color.green, null)
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
            val database = activity?.applicationContext?.let { CoinDatabase.getDatabase(it) }
            descriotionCoinViewModel.sendCoinToDatabase(coin, database)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return super.onOptionsItemSelected(item)
        when (item.itemId) {
            R.id.favorite -> {

            }
        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}