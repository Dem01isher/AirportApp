package com.leskov.airport.base.list_adapter

interface ListController<T> {

    fun addItem(item: T)

    fun setOnItemClickListener(item: ((T) -> Unit))

    fun setOnLongItemClickListener(position: (Int) -> Unit)

    fun submitList(list: List<T>)

    fun removeItem(position: Int, item: T)

    fun clear()
}