package com.leskov.airport.base.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope

@HiltViewModel
abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading : LiveData<Boolean> = _loading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    protected suspend fun <T> launchSingleRequest(
        liveData: MutableLiveData<T>,
        context: CoroutineScope = viewModelScope,
        block: suspend (MutableLiveData<T>.() -> Unit)
    ) = context.runCatching {
        _loading.postValue(true)
        block.invoke(liveData)
    }.onSuccess {
        _loading.postValue(false)
    }.onFailure {
        _loading.postValue(false)
        _errorMessage.postValue(it.message)
    }
}