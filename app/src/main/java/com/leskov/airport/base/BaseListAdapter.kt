package com.leskov.airport.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.Delegates

abstract class BaseListAdapter<T, Binding: ViewDataBinding>()
    : RecyclerView.Adapter<BindingHolder<Binding>>(), ListController<T> {

    @get:LayoutRes
    protected abstract val layoutId: Int

    protected var currentList: MutableList<T> = mutableListOf()

    private var position by Delegates.notNull<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<Binding> =
        BindingHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))

    override fun onBindViewHolder(holder: BindingHolder<Binding>, position: Int) {
        this.position = holder.bindingAdapterPosition
        initListeners(holder.binding, currentList[holder.bindingAdapterPosition])
    }

    protected abstract fun initListeners(binding: Binding, item: T)

    override fun submitList(list: List<T>) {
        this.currentList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun addItem(item: T) {
        this.currentList.add(item)
        notifyDataSetChanged()
    }

    override fun removeItem(item: T) {
        this.currentList.remove(item)
        notifyItemRemoved(position)
    }

    override fun clear() {
        this.currentList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = currentList.size
}