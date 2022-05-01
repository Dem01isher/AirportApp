package com.leskov.airport.presentation.list_of_items

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.event.postEvent
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.domain.use_case.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListOfItemsViewModel @Inject constructor(
    private val selectListTypeUseCase: SelectListTypeUseCase,
    private val searchDataByTypeUseCase: SearchDataByTypeUseCase,
    private val removeItemByTypeUseCase: RemoveItemByTypeUseCase,
    private val updateItemByTypeUseCase: UpdateItemByTypeUseCase,
    private val findItemByTypeUseCase: FindItemByTypeUseCase
) :
    BaseViewModel() {

    private val _listOfData =
        MutableLiveData<List<Any?>>()
    val listOfData: LiveData<List<Any?>> = _listOfData

    private val _updatedList =
        MutableLiveData<List<Any?>>()
    val updatedList: LiveData<List<Any?>> = _updatedList

    private val _updateListState = MutableLiveData<Boolean>()
    val updateListState : LiveData<Boolean> = _updateListState

    init {
        fetchSelectedTypeData()
    }

    fun setType(type: String) {
        updateItemByTypeUseCase.selectType(type)
        selectListTypeUseCase.selectType(type)
        searchDataByTypeUseCase.selectType(type)
        removeItemByTypeUseCase.selectType(type)
    }

    fun fetchSelectedTypeData() {
        _loading.postEvent(true)
        _listOfData.postValue(selectListTypeUseCase.fetchSelectedTypeList())
        _loading.postEvent(false)
    }

    fun getItemByKey(key: Any?){
        findItemByTypeUseCase.findItemByType(key)
    }

    fun searchDataByType(searchQuery: String){
        if (searchQuery.isNullOrBlank()) return
        viewModelScope.launch {
            _loading.postEvent(true)
            _updateListState.postValue(true)
            try {
                delay(1500)
                _updatedList.postValue(searchDataByTypeUseCase.searchSelectedTypeData(searchQuery))
                delay(1500)
                _loading.postEvent(false)
                _updateListState.postValue(false)
            } catch (e: Exception){
                _loading.postEvent(false)
                _updateListState.postValue(true)
                Timber.e(e)
            }
        }
    }

    fun removeItemByType(item: Any?){
        viewModelScope.launch(dispatcherController.launchInMain()) {
            _loading.postEvent(true)
            removeItemByTypeUseCase.removeSelectedTypeItem(item)
            _loading.postEvent(false)
        }
    }

    override fun onCleared() {
        super.onCleared()
        _updatedList.value = null
        _listOfData.value = null
        _updateListState.value = null
    }

}