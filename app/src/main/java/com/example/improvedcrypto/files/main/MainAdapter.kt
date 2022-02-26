package com.example.improvedcrypto.files.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.files.main.dataclass.CoinResponse
import com.example.improvedcrypto.files.repository.TemporaryId

class MainAdapter(
    private val coinList: List<CoinResponse>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_coin, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    class MainViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

//        private val mainFragment = MainFragment()

        //        var idRepository: IdRepository = IdRepository()
        var temporaryId = TemporaryId()
        private val name: TextView = itemView.findViewById(R.id.tv_Name)
        private val avatar: ImageView = itemView.findViewById((R.id.iv_Avatar))
        private val symbol: TextView = itemView.findViewById(R.id.tv_Symbol)
        private val price: TextView = itemView.findViewById(R.id.tv_Price)

        fun bind(item: CoinResponse) {
            name.text = item.name
            symbol.text = item.symbol
            price.text = item.price

            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.ic_no_image)
                .into(avatar)

            itemView.setOnClickListener {
                temporaryId.id = item.id
                findNavController(itemView).navigate(R.id.action_mainFragment_to_descriptionCoinFragment)

            }
        }
    }


}