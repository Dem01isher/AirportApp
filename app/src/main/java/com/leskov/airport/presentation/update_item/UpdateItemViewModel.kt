package com.leskov.airport.presentation.update_item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.leskov.airport.base.view_model.BaseViewModel
import com.leskov.airport.domain.use_case.FindItemByTypeUseCase
import com.leskov.airport.domain.use_case.UpdateItemByTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateItemViewModel @Inject constructor(
    private val updateItemByTypeUseCase: UpdateItemByTypeUseCase,
    private val findItemByTypeUseCase: FindItemByTypeUseCase
) : BaseViewModel() {

    private val _updateItem = MutableLiveData<Any>()
    val updateItem : LiveData<Any> = _updateItem

    private val _selectedType = MutableLiveData<String>()
    val selectedType : LiveData<String> = _selectedType

    fun setType(type: String){
        updateItemByTypeUseCase.selectType(type)
        findItemByTypeUseCase.selectType(type)
        _selectedType.postValue(type)
    }

    fun updateSelectedTypeData(item: Any?) {
        viewModelScope.launch {
            updateItemByTypeUseCase.updateSelectedTypeItem(item)
        }
    }

    fun getItemByKey(key: Any?){
        _updateItem.postValue(findItemByTypeUseCase.findItemByType(key))
    }
}