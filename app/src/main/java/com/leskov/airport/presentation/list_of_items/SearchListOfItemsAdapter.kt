package com.leskov.airport.presentation.list_of_items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.leskov.airport.base.extensions.updateVisibility
import com.leskov.airport.base.list_adapter.BaseListAdapter
import com.leskov.airport.base.view_holder.BaseViewHolder
import com.leskov.airport.databinding.*
import com.leskov.airport.domain.entity.*
import java.util.*

class SearchListOfItemsAdapter : ListAdapter<Any?, BaseViewHolder>(BaseListAdapter.DiffUtilCallbacks()) {

    companion object {
        private const val AIR_COMPANY_VIEW_HOLDER = 0
        private const val AIRPLANE_VIEW_HOLDER = 1
        private const val AIRPORT_VIEW_HOLDER = 2
        private const val RACE_VIEW_HOLDER = 3
        private const val HEAD_QUARTER_VIEW_HOLDER = 4
        private const val INSURANCE_VIEW_HOLDER = 5
        private const val ROUTE_VIEW_HOLDER = 6
        private const val TEAM_VIEW_HOLDER = 7
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            AIR_COMPANY_VIEW_HOLDER -> AirCompanyViewHolder(
                ListItemAircompanyBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            AIRPORT_VIEW_HOLDER -> AirPortViewHolder(
                ListItemAirportBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            AIRPLANE_VIEW_HOLDER -> AirPlaneViewHolder(
                ListItemAirplaneBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            HEAD_QUARTER_VIEW_HOLDER -> HeadQuarterViewHolder(
                ListItemHeadquarterBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            INSURANCE_VIEW_HOLDER -> InsuranceViewHolder(
                ListItemInsuranceBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

            TEAM_VIEW_HOLDER -> TeamViewHolder(
                ListItemTeamBinding.inflate(
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
            currentList[position] is AirCompanyEntity -> AIR_COMPANY_VIEW_HOLDER
            currentList[position] is AirplaneEntity -> AIRPLANE_VIEW_HOLDER
            currentList[position] is AirportEntity -> AIRPORT_VIEW_HOLDER
            currentList[position] is RaceEntity -> RACE_VIEW_HOLDER
            currentList[position] is HeadQuarterEntity -> HEAD_QUARTER_VIEW_HOLDER
            currentList[position] is InsuranceEntity -> INSURANCE_VIEW_HOLDER
            currentList[position] is RouteEntity -> ROUTE_VIEW_HOLDER
            currentList[position] is TeamEntity -> TEAM_VIEW_HOLDER
            else -> 0
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        when (holder.itemViewType) {
            AIR_COMPANY_VIEW_HOLDER -> (holder as AirCompanyViewHolder).bind(holder.bindingAdapterPosition)
            AIRPLANE_VIEW_HOLDER -> (holder as AirPlaneViewHolder).bind(holder.bindingAdapterPosition)
            AIRPORT_VIEW_HOLDER -> (holder as AirPortViewHolder).bind(holder.bindingAdapterPosition)
            RACE_VIEW_HOLDER -> (holder as RaceViewHolder).bind(holder.bindingAdapterPosition)
            HEAD_QUARTER_VIEW_HOLDER -> (holder as HeadQuarterViewHolder).bind(holder.bindingAdapterPosition)
            INSURANCE_VIEW_HOLDER -> (holder as InsuranceViewHolder).bind(holder.bindingAdapterPosition)
            ROUTE_VIEW_HOLDER -> (holder as RouteViewHolder).bind(holder.bindingAdapterPosition)
            TEAM_VIEW_HOLDER -> (holder as TeamViewHolder).bind(holder.bindingAdapterPosition)
        }
    }

    override fun submitList(list: MutableList<Any?>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun getItemCount(): Int {
        return currentList.size ?: 0
    }

    private inner class TeamViewHolder(val binding: ListItemTeamBinding) : BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            val team = currentList[position] as TeamEntity
            binding.numberOf.text = "${team.numberOf.toString().toInt()}"
            binding.countOfPilots.text = "${team.countOfPilots.toString().toInt()}"
            binding.countOfEngineers.text = "${team.countOfEngineers.toString().toInt()}"
            binding.countOfAttendants.text = "${team.countFlightAttendants.toString().toInt()}"
            binding.countOfPeople.text = "${
                team.countOfPilots.toString().toInt() + team.countFlightAttendants.toString()
                    .toInt() + team.countOfEngineers.toString()
                    .toInt() + team.numberOfMovers.toString().toInt()
            }"
            binding.apply {
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
        }
    }

    private inner class RouteViewHolder(val binding: ListItemRouteBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.item = currentList[position] as RouteEntity
            binding.apply {
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
        }
    }

    private inner class InsuranceViewHolder(val binding: ListItemInsuranceBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.item = currentList[position] as InsuranceEntity
            binding.apply {
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
        }
    }

    private inner class HeadQuarterViewHolder(val binding: ListItemHeadquarterBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.item = currentList[position] as HeadQuarterEntity

            binding.apply {
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
        }

    }

    private inner class AirCompanyViewHolder(val binding: ListItemAircompanyBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            val airCompany = currentList[position] as AirCompanyEntity
            binding.apply {
                title.text = airCompany.nameOf
                type.text = airCompany.officeLocation
                countOfLanes.text = airCompany.countOfLanes.toString()
                model.text = airCompany.dateOfFoundation
                loadCapacity.text = airCompany.typeOf
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
        }

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
        }
    }

    private inner class AirPlaneViewHolder(val binding: ListItemAirplaneBinding) :
        BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {
            binding.item = currentList[position] as AirplaneEntity
            binding.apply {
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
        }
    }

    private inner class RaceViewHolder(val binding: ListItemRaceBinding) : BaseViewHolder(binding) {

        private var isVisible: Boolean = true

        override fun bind(position: Int) {

            binding.item = currentList[position] as RaceEntity
            binding.apply {
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
        }
    }
}