package com.example.improvedcrypto.files.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.files.data.Coin
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters


class FavoriteAdapter(
    private val coinList:  List<DatabaseParameters>
) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favoritecoin, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    class FavoriteViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_Name)
        private val avatar: ImageView = itemView.findViewById(R.id.iv_Avatar)
        private val symbols: TextView = itemView.findViewById(R.id.tv_Symbol)
        private val price: TextView = itemView.findViewById(R.id.tv_Price)
        private val changePrice: TextView = itemView.findViewById(R.id.tv_ChangePrice)
        private val description: TextView = itemView.findViewById(R.id.tv_Description)


        fun bind(item: DatabaseParameters) {

            name.text = item.name
            symbols.text = item.symbol
            price.text = item.currentPrice.toString()
            changePrice.text = item.changePrice.toString()
            description.text = item.description

            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.ic_no_image)
                .into(avatar)
        }
    }
}
