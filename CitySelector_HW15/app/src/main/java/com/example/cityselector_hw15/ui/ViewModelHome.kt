package com.example.cityselector_hw15.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cityselector_hw15.data.model.City

class ViewModelHome:ViewModel() {


    private var mainCityList = mutableListOf(
        City("Babol", false), City("Amol", false),
        City("Sari", false), City("Chalous", false),
        City("Neka", false), City("Behshar", false),
        City("Shahsavar", false), City("Ramsar", false),
        City("Galuoga", false), City("Nour", false),
    )
    private val favoriteList = mutableListOf<City>()
    var favoriteListLiveData = MutableLiveData<MutableList<City>>(mutableListOf())
    val cityListLiveData = MutableLiveData(mainCityList)



    fun selectCity(position: Int, selected: Boolean) {
        if (selected) {
            favoriteList.remove(mainCityList[position])
            favoriteListLiveData.value = favoriteList
        } else {
            favoriteList.add(mainCityList[position])
            favoriteListLiveData.value = favoriteList
        }
        mainCityList[position].selected = mainCityList[position].selected.not()
        cityListLiveData.value?.get(position)?.selected = mainCityList[position].selected
    }

    fun removeCityItem(position: Int) {
        favoriteList.removeAt(position)
        favoriteListLiveData.value = favoriteList

        mainCityList[position].selected = mainCityList[position].selected.not()
        cityListLiveData.value = mainCityList
    }

}