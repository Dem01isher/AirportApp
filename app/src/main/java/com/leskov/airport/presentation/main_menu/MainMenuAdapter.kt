package com.leskov.airport.presentation.main_menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.databinding.ListItemMenuBinding
import com.leskov.airport.domain.model.MainMenuItemType

class MainMenuAdapter : RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder>() {

    val list: MutableList<MainMenuItemType> = mutableListOf(
        MainMenuItemType.AIRCOMPANY,
        MainMenuItemType.AIRPLANE,
        MainMenuItemType.AIRPORT,
        MainMenuItemType.HEADQUARTERS,
        MainMenuItemType.INSURANCE,
        MainMenuItemType.RACE,
        MainMenuItemType.ROUTE,
        MainMenuItemType.TEAM
    )

    private var onClickListener: ((Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainMenuViewHolder =
        MainMenuViewHolder(ListItemMenuBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: MainMenuViewHolder, position: Int) {
        holder.bind(holder.bindingAdapterPosition)
    }

    override fun getItemCount(): Int = list.size

    inner class MainMenuViewHolder(val binding: ListItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = list[position]
            binding.title.setText(item.titleRes)
            binding.cvRoot.setOnClickWithDebounce {
                onClickListener?.invoke(position)
            }
        }
    }

    fun setOnItemClickListener(listener: (Int) -> Unit){
        this.onClickListener = listener
    }
}