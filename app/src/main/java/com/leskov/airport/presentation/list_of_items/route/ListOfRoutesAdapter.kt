package com.leskov.airport.presentation.list_of_items.route

import com.leskov.airport.R
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.databinding.ListItemRouteBinding
import com.leskov.airport.domain.entity.RouteEntity

class ListOfRoutesAdapter : BaseListAdapter<RouteEntity, ListItemRouteBinding>() {

    override val layoutId: Int
        get() = R.layout.list_item_route

    private var onClickListener : ((RouteEntity) -> Unit)? = null
    private var isVisible: Boolean = true

    override fun onViewHolderCreated(binding: ListItemRouteBinding, position: Int) {
        val route = currentList[position]
        binding.apply {
            title.text = route.numberOf.toString()
            departureOfCountry.text = route.departureCountry
            model.text = route.destinationCountry
            status.text = route.status
            length.text = route.length
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

    fun setOnItemClickListener(listener : (RouteEntity) -> Unit){
        onClickListener = listener
    }
}