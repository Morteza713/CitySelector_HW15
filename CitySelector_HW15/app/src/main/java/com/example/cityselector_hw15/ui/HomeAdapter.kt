package com.example.cityselector_hw15.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cityselector_hw15.R
import com.example.cityselector_hw15.data.model.City
import com.example.cityselector_hw15.databinding.CityItemBinding

class HomeAdapter(
    private val cityList: MutableList<City>,
    private val adapterClick: ClickHandler
):RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    inner class HomeViewHolder(private val binding: CityItemBinding):RecyclerView.ViewHolder(binding.root)
    ,View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        fun bind(city: City) {
            binding.tvCityName.text = city.name
            if (city.selected)
                binding.tvCityName.setBackgroundColor(Color.parseColor("#FF00FF"))
            else
                binding.tvCityName.setBackgroundColor(Color.parseColor("#FFFFFF"))
        }
        override fun onClick(p0: View?) {
            adapterClick.click(bindingAdapterPosition, cityList[bindingAdapterPosition].selected)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = CityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(cityList[position])
    }

    override fun getItemCount(): Int {
        return cityList.size
    }

}
//interface AdapterClick {
//    fun click(position: Int, selected: Boolean)
//}