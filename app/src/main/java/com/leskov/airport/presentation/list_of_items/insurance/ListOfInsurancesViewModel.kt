package com.leskov.airport.presentation.list_of_items.insurance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.live_data.SingleEventLiveData
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.data.repository.InsuranceRepository
import com.leskov.airport.domain.entity.InsuranceEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListOfInsurancesViewModel @Inject constructor(
    private val repository: InsuranceRepository
) : BaseViewModel() {

    val list by lazy { repository.fetchAllData() }

    private val _insurance = MutableLiveData<InsuranceEntity>()
    val insurance: LiveData<InsuranceEntity> = _insurance

    fun updateItem(item: InsuranceEntity){
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun findItemByKey(key: String){
        _insurance.postValue(repository.findItemByKey(key))
    }

    fun searchData(searchQuery: String): LiveData<List<InsuranceEntity?>>{
        return repository.searchData(searchQuery)
    }

    fun removeItem(item: InsuranceEntity){
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}