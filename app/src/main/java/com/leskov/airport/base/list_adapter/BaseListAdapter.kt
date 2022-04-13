package com.leskov.airport.base.list_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.base.view_holder.BindingHolder
import kotlin.properties.Delegates

abstract class BaseListAdapter<T, Binding: ViewDataBinding>
    : RecyclerView.Adapter<BindingHolder<Binding>>(), ListController<T> {

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var currentList: MutableList<T> = mutableListOf()

    private var position by Delegates.notNull<Int>()

    private var itemClickListener : ((T) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<Binding> =
        BindingHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false))

    override fun onBindViewHolder(holder: BindingHolder<Binding>, position: Int) {
        this.position = holder.bindingAdapterPosition
        initListeners(holder.binding, currentList[holder.bindingAdapterPosition])
        itemClickListener?.invoke(currentList[holder.bindingAdapterPosition])
    }

    override fun getItemCount(): Int = currentList.size

    protected abstract fun initListeners(binding: Binding, item: T)

    override fun setOnItemClickListener(listener: ((T) -> Unit)) {
        this.itemClickListener = listener
    }

    override fun submitList(list: List<T>) {
        this.currentList = list.toMutableList()
        notifyDataSetChanged()
    }

    override fun addItem(item: T) {
        this.currentList.add(item)
        notifyDataSetChanged()
    }

    override fun removeItem(position: Int) {
        this.currentList.remove(currentList[position])
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, currentList.size)
    }

    override fun clear() {
        this.currentList.clear()
        notifyDataSetChanged()
    }
}