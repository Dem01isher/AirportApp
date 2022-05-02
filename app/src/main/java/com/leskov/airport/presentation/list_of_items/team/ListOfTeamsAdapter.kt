package com.leskov.airport.presentation.list_of_items.team

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemTeamBinding
import com.leskov.airport.domain.entity.TeamEntity

class ListOfTeamsAdapter : BaseListAdapter<TeamEntity, ListItemTeamBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_team

    private var onClickListener: ((TeamEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemTeamBinding, position: Int) {
        binding.apply {
            item = currentList[position]
            headerLayout.setOnClickListener {
                if (isVisible) {
                    descriptionLayout.updateVisibility(true)
                    arrow.rotation = 360f
                    isVisible = false
                } else {
                    descriptionLayout.updateVisibility(false)
                    arrow.rotation = 270f
                    isVisible = true
                }
            }
            cardContainer.setOnClickListener {
                if (isVisible) {
                    descriptionLayout.updateVisibility(true)
                    arrow.rotation = 360f
                    isVisible = false
                } else {
                    descriptionLayout.updateVisibility(false)
                    arrow.rotation = 270f
                    isVisible = true
                }
            }
        }
        binding.cardContainer.setOnLongClickListener {
            onClickListener?.invoke(currentList[position])
            false
        }
    }

    fun setOnItemClickListener(listener: (TeamEntity) -> Unit){
        onClickListener = listener
    }
}