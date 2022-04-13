package com.leskov.airport.base.list_adapter

interface ListController<T> {

    fun addItem(item: T)

    fun setOnItemClickListener(item: ((T) -> Unit))

    fun submitList(list: List<T>)

    fun removeItem(position: Int)

    fun clear()
}