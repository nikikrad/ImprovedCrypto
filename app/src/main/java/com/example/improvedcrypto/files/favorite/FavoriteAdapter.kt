package com.example.improvedcrypto.files.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.improvedcrypto.R
import com.example.improvedcrypto.databinding.FragmentFavoriteBinding
import com.example.improvedcrypto.files.data.dataclass.DatabaseParameters


class FavoriteAdapter(
    private val coinList: List<DatabaseParameters>,
    private val binding: FragmentFavoriteBinding,
    private val favoriteViewModel: FavoriteViewModel,
    private val applicationContext: Context

) : RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_favoritecoin, parent, false)
        return FavoriteViewHolder(view, binding, favoriteViewModel, applicationContext)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(coinList[position])
    }

    override fun getItemCount(): Int = coinList.size

    class FavoriteViewHolder(
        itemView: View,
        private val binding: FragmentFavoriteBinding,
        private val favoriteViewModel: FavoriteViewModel,
        private val applicationContext: Context
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.tv_Name)
        private val avatar: ImageView = itemView.findViewById(R.id.iv_Avatar)
        private val symbols: TextView = itemView.findViewById(R.id.tv_Symbol)
        private val description: TextView = itemView.findViewById(R.id.tv_Description)


        fun bind(item: DatabaseParameters) {

            name.text = item.name
            symbols.text = item.symbol
            description.text = item.description

            Glide.with(itemView)
                .load(item.image)
                .placeholder(R.drawable.ic_no_image)
                .into(avatar)

            itemView.setOnClickListener {
                val delete = FavoriteFragment()
                delete.showSnackBar(binding, item, favoriteViewModel, applicationContext)

            }
        }
    }
}
