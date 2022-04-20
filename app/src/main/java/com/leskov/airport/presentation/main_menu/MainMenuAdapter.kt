package com.leskov.airport.presentation.main_menu

import com.leskov.airport.R
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.databinding.ListItemMenuBinding
import com.leskov.airport.domain.model.MainMenuModel

class MainMenuAdapter : BaseListAdapter<MainMenuModel, ListItemMenuBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_menu

    private var onClickListener : ((String) -> Unit)? = null

    override fun onViewHolderCreated(binding: ListItemMenuBinding, position: Int) {
        val menu = getItem(position)

        binding.btnMenu.title.text = menu.title
        binding.btnMenu.icon.setImageResource(menu.icon)

        binding.btnMenu.crRoot.setOnClickWithDebounce {
            onClickListener?.invoke(menu.title)
        }
    }

    fun onItemClickListener(listener: (String) -> Unit){
        onClickListener = listener
    }
}