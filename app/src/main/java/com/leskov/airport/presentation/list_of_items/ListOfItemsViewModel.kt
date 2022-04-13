package com.leskov.airport.presentation.list_of_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.AirportRepository
import com.leskov.airport.domain.AirportEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListOfItemsViewModel @Inject constructor(private val repository: AirportRepository) : BaseViewModel() {

    private val _listOfData = MutableLiveData<List<AirportEntity>>()
    val listOfData : LiveData<List<AirportEntity>> = _listOfData

    init {
        fetchAllData()
    }

    private fun fetchAllData(){
        viewModelScope.launch {
            launchSingleRequest(_listOfData){
                postValue(repository.fetchAllData())
            }
        }
    }
}