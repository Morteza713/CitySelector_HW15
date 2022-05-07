package com.example.cityselector_hw15.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cityselector_hw15.data.model.City
import com.example.cityselector_hw15.databinding.CityItemBinding

class SecondAdapter(private val cityList: MutableList<City>):RecyclerView.Adapter<SecondAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(private val binding: CityItemBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(city: City) {
            binding.tvCityName.text = city.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = CityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }
}