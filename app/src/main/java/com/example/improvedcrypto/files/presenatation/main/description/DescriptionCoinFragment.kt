package com.example.improvedcrypto.files.presenatation.main.description

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding
import com.example.improvedcrypto.files.data.CoinEntity
import com.example.improvedcrypto.files.presenatation.main.dataclass.CoinItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject

class DescriptionCoinFragment : Fragment() {


    private lateinit var binding: FragmentDescriptionBinding
    private val descriptionCoinViewModel: DescriptionCoinViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getString("ID")?.let { descriptionCoinViewModel.getDescriptionResponse(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        descriptionCoinViewModel.liveData.observe(viewLifecycleOwner){

            binding.pbProgressBar.isVisible = it.name.isEmpty()

            Glide.with(view)
                .load(it.image.large)
                .placeholder(R.drawable.ic_no_image)
                .into(binding.ivAvatar)

            binding.tvName.text = it.name

            binding.tvSymbol.text = it.symbol

            binding.tvChangePrice.text = it.marketData.changePrice.toString() + " %"

            val resource: Resources = resources
            val textRedColor = resource.getColor(R.color.red, null)
            val textGreenColor = resource.getColor(R.color.green, null)
            if (it.marketData.changePrice > 0) binding.tvChangePrice.setTextColor(textGreenColor)
            else binding.tvChangePrice.setTextColor(textRedColor)

            binding.tvPrice.text = it.marketData.currentPrice.usd.toString() + " $"

            val adapter = DescriptionCoinAdapter(it.description.en)
            binding.rvDescription.layoutManager =
                LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            binding.rvDescription.adapter = adapter

            arguments?.getString("ID")

            val coinList: MutableList<CoinItem> = getAllData()
            var status: Boolean = descriptionCoinViewModel.processingDatabaseResponse(
                arguments?.getString("ID"),
                coinList
            )
            if (status) binding.ibLike.setImageResource(R.drawable.ic_star_rate)
            else binding.ibLike.setImageResource(R.drawable.ic_star_outline)

            val coin = CoinEntity(
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
                if (status) {
                    deleteCoin(coin)
                    binding.ibLike.setImageResource(R.drawable.ic_star_outline)
                    Toast.makeText(
                        context,
                        coin.name + " is successfully deleted!",
                        Toast.LENGTH_SHORT
                    ).show()
                    status = false
                } else {
                    insertToDataBase(coin)
                    binding.ibLike.setImageResource(R.drawable.ic_star_rate)
                    Toast.makeText(
                        context,
                        coin.name + " is successfully added!",
                        Toast.LENGTH_SHORT
                    ).show()
                    status = true
                }

            }

            val clickAnimation: Animation =
                AnimationUtils.loadAnimation(activity?.applicationContext, R.anim.click_alpha)
            binding.btnBack.setOnClickListener {
                binding.btnBack.startAnimation(clickAnimation)
                Navigation.findNavController(view)
                    .navigate(R.id.mainFragment)
            }
        }
    }

    private fun getAllData(): MutableList<CoinItem> {
        var coinList: MutableList<CoinItem> = emptyList<CoinItem>().toMutableList()
        lifecycleScope.launch(Dispatchers.IO) {
            runBlocking {
                coinList = descriptionCoinViewModel.getAllData()
            }
        }
        return coinList
    }


    private fun insertToDataBase(coinEntity: CoinEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            descriptionCoinViewModel.sendCoinToDatabase(coinEntity)
        }
    }

    private fun deleteCoin(coinEntity: CoinEntity) {
        lifecycleScope.launch(Dispatchers.IO) {
            descriptionCoinViewModel.deleteCoin(coinEntity)
        }
    }
}