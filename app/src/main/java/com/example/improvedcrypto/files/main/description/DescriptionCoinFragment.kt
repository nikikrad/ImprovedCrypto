package com.example.improvedcrypto.files.main.description

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.CoinDatabase
import com.example.improvedcrypto.files.data.CoinDatabase.Companion.getDatabase
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters
import com.example.improvedcrypto.files.data.repository.CoinRepository
import com.example.improvedcrypto.files.favorite.FavoriteViewModel
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
        val view = inflater.inflate(R.layout.fragment_description, container, false)

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


            val coinList: MutableList<DatabaseParameters> = getAllData()
            var status: Boolean = descriotionCoinViewModel.processingDatabaseResponse(arguments?.getString("ID"), coinList)
            if(status == true)binding.ibLike.setImageResource(R.drawable.ic_star_rate)
            else binding.ibLike.setImageResource(R.drawable.ic_star_outline)

            val coin = Coin(
                0,
                it.id,
                it.symbol,
                it.name,
                it.image.large,
                it.description.en,
                it.marketData.currentPrice.usd,
                it.marketData.changePrice
            )

            binding.ibLike.setOnClickListener {
                if(status == true){
                    deleteCoin(coin)
                    binding.ibLike.setImageResource(R.drawable.ic_star_outline)
                    Toast.makeText(context, coin.name + " is successfully deleted!", Toast.LENGTH_SHORT).show()
                    status = false
                }else{
                    insertToDataBase(coin)
                    binding.ibLike.setImageResource(R.drawable.ic_star_rate)
                    Toast.makeText(context, coin.name + " is successfully added!", Toast.LENGTH_SHORT).show()
                    status = true
                }

            }

            val clickAnimation: Animation = AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.click_alpha)
            binding.btnBack.setOnClickListener {
                binding.btnBack.startAnimation(clickAnimation)
                Navigation.findNavController(view)
                    .navigate(R.id.action_descriptionCoinFragment_to_mainFragment)
            }
        })
    }

    fun getAllData(): MutableList<DatabaseParameters>  {
        var coinList: MutableList<DatabaseParameters> = emptyList<DatabaseParameters>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { getDatabase(it) }
            coinList = descriotionCoinViewModel.getAllData(database)
        }
        Thread.sleep(100)
        return coinList
    }


    fun insertToDataBase(coin: Coin) {

        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { CoinDatabase.getDatabase(it) }
            descriotionCoinViewModel.sendCoinToDatabase(coin, database)
        }
    }

    fun deleteCoin(coin: Coin) {
        lifecycleScope.launch(Dispatchers.IO) {
            val database = activity?.applicationContext?.let { getDatabase(it) }
            descriotionCoinViewModel.deleteCoin(coin, database)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
    }
}