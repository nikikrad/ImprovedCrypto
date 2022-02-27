package com.example.improvedcrypto.files.main.description

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentDescriptionBinding
import com.example.improvedcrypto.files.main.description.dataclass.ResponseDescription

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
            binding.tvChangePrice.setText(it.marketData.changePrice.toString())
            binding.tvPrice.setText(it.marketData.currentPrice.usd.toString())
            binding.tvDescription.setText(it.description.en)
        })

//        Log.e("TAG", bodyCoin.toString())




    }

    override fun onDestroy() {
        super.onDestroy()
    }
}