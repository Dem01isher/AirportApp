package com.leskov.airport.base.list_adapter

interface ListController<T> {

    fun addItem(item: T)

    fun setOnItemClickListener(item: ((T) -> Unit))

    fun setOnLongItemClickListener(position: (Int) -> Unit)

    fun removeItem(item: T)

    fun clear()
}