package com.leskov.airport.presentation.list_of_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.databinding.ListItemAirplaneBinding
import com.leskov.airport.databinding.ListItemAirportBinding
import com.leskov.airport.databinding.ListItemRaceBinding
import com.leskov.airport.domain.entity.AirplaneEntity
import com.leskov.airport.domain.entity.AirportEntity
import com.leskov.airport.domain.entity.RaceEntity

class ListOfItemsAdapter : RecyclerView.Adapter<ListOfItemsAdapter.BaseViewHolder>() {

    var currentList: MutableList<Any?> = arrayListOf()

    companion object {
        private const val AIRPLANE_VIEW_HOLDER = 1
        private const val AIRPORT_VIEW_HOLDER = 2
        private const val RACE_VIEW_HOLDER = 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            AIRPLANE_VIEW_HOLDER -> AirPlaneViewHolder(
                ListItemAirplaneBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            AIRPORT_VIEW_HOLDER -> AirPortViewHolder(
                ListItemAirportBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
            else -> RaceViewHolder(
                ListItemRaceBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

    override fun getItemViewType(position: Int): Int =
        when {
            currentList[position] is AirplaneEntity -> AIRPLANE_VIEW_HOLDER
            currentList[position] is AirportEntity -> AIRPORT_VIEW_HOLDER
            currentList[position] is RaceEntity -> RACE_VIEW_HOLDER
            else -> 0
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder.itemViewType) {
            AIRPLANE_VIEW_HOLDER -> (holder as AirPlaneViewHolder).bind(position)
            AIRPORT_VIEW_HOLDER -> (holder as AirPortViewHolder).bind(position)
            RACE_VIEW_HOLDER -> (holder as RaceViewHolder).bind(position)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size ?: 0
    }

    private inner class AirPortViewHolder(val binding: ListItemAirportBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.apply {
                val airport = currentList[position] as AirportEntity
                title.text = airport.title
                city.text = airport.city
                countOfTerminals.text = airport.countOfTerminals.toString()
                countryLocation.text = airport.countryLocation
                headerLayout.setOnClickWithDebounce {
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
                cardContainer.setOnClickWithDebounce {
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
        }
    }

    private inner class AirPlaneViewHolder(val binding: ListItemAirplaneBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.item = currentList[position] as AirplaneEntity
            binding.apply {
                headerLayout.setOnClickWithDebounce {
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
                cardContainer.setOnClickWithDebounce {
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
        }
    }

    private inner class RaceViewHolder(val binding: ListItemRaceBinding) : BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {

            binding.item = currentList[position] as RaceEntity
            binding.apply {
                headerLayout.setOnClickWithDebounce {
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
                cardContainer.setOnClickWithDebounce {
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
        }
    }

    abstract inner class BaseViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(position: Int)
    }

}