package com.leskov.airport.base.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.leskov.airport.base.event.Event
import com.leskov.airport.base.event.EventObserver

fun <T> LiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer{
        it?.let(observer)
    })
}

fun <T> MutableLiveData<T>.nonNullObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, Observer{
        it?.let(observer)
    })
}

fun <T> LiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, EventObserver{
        it?.let(observer)
    })
}

fun <T> MutableLiveData<Event<T>>.eventObserve(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner, EventObserver{
        it?.let(observer)
    })
}