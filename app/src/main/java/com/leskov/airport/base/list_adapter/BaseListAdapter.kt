package com.leskov.airport.base.list_adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.view_holder.BindingHolder

abstract class BaseListAdapter<T, Binding : ViewDataBinding> :
    ListAdapter<T,BindingHolder<Binding>>(DiffUtilCallbacks<T>()), ListController<T> {

    @get:LayoutRes
    protected abstract val layoutId: Int

    private var itemClickListener: ((T) -> Unit)? = null

    private var longItemClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<Binding> =
        BindingHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BindingHolder<Binding>, position: Int) {
        initListeners(holder.binding, holder.bindingAdapterPosition)
        onViewHolderCreated(holder.binding, holder.bindingAdapterPosition)
    }

    private fun initListeners(binding: Binding, position: Int) {
        binding.root.setOnClickWithDebounce {
            itemClickListener?.invoke(currentList[position])
        }

//        binding.root.setOnLongClickListener {
//            longItemClickListener?.invoke(position)
//            false
//        }
    }

    protected abstract fun onViewHolderCreated(binding: Binding, position: Int)

    override fun addItem(item: T) {
        currentList.add(item)
    }

    override fun removeItem(item: T) {
        currentList.remove(item)
    }

    override fun clear() {
        currentList.clear()
    }

    class DiffUtilCallbacks<T> : DiffUtil.ItemCallback<T>() {

        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean =
            oldItem == newItem
    }
}