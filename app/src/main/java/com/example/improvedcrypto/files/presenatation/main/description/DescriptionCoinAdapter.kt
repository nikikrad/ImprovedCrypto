package com.example.improvedcrypto.files.presenatation.main.description

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.improvedcrypto.R

class DescriptionCoinAdapter(
    private val coin: String
): RecyclerView.Adapter<DescriptionCoinAdapter.DescriptionCoinViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DescriptionCoinViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_description, parent, false)
        return DescriptionCoinViewHolder(view)
    }

    override fun onBindViewHolder(holder: DescriptionCoinViewHolder, position: Int) {
        holder.bind(coin)
    }

    override fun getItemCount(): Int = 1

    class DescriptionCoinViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val description: TextView = itemView.findViewById(R.id.tv_Description)

        fun bind(item: String) {
            description.text = item

        }

    }
}