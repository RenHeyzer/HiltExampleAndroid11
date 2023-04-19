package com.radin.presentation.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.radin.presentation.R
import com.radin.presentation.databinding.ItemCountryBinding
import com.radin.presentation.models.CountryUI

class CountriesAdapter :
    ListAdapter<CountryUI, CountriesAdapter.CountryViewHolder>(
        diffUtil
    ) {

    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("StringFormatInvalid")
        fun onBind(item: CountryUI) = with(binding) {
            Glide.with(ivCountryFlag.context)
                .load(item.flags.png)
                .into(ivCountryFlag)
            tvCountryName.text = item.name
            itemView.tag = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            ItemCountryBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<CountryUI>() {
            override fun areItemsTheSame(oldItem: CountryUI, newItem: CountryUI): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: CountryUI, newItem: CountryUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}