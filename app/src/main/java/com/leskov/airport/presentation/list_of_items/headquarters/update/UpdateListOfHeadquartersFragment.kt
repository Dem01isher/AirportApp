package com.leskov.airport.presentation.list_of_items.headquarters.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfHeadquartersBinding
import com.leskov.airport.domain.entity.HeadQuarterEntity
import com.leskov.airport.presentation.list_of_items.headquarters.ListOfHeadquartersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfHeadquartersFragment :
    BaseVMFragment<ListOfHeadquartersViewModel, FragmentUpdateListOfHeadquartersBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_headquarters

    override val viewModel: ListOfHeadquartersViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getInt("key") ?: 0)

        binding.btnConfirm.title.setText(R.string.update)

        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = HeadQuarterEntity(
                    numberOf = arguments?.getInt("key") ?: 0,
                    availabilityOfKitchen = if (binding.cbAvailabilityOfKitchen.isChecked) getString(
                        R.string.kitchen_is_available
                    ) else getString(R.string.kitchen_is_disabled),
                    countOfLevels = binding.typeOf.text.toString().toInt(),
                    numberOfBeds = binding.officeLocation.text.toString().toInt(),
                    entertainmentRoom = if (binding.cbEntertainmentRoom.isChecked) getString(R.string.have_entertainment_room) else getString(
                        R.string.no_entertainment_room
                    )
                )
            )
            navController.popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.headQuarter.eventObserve(viewLifecycleOwner) {
            binding.headquarters = it
            binding.officeLocation.setText(it.numberOfBeds.toString())
            binding.cbAvailabilityOfKitchen.isChecked = it.availabilityOfKitchen == getString(R.string.kitchen_is_available)
            binding.cbEntertainmentRoom.isChecked = it.entertainmentRoom == getString(R.string.have_entertainment_room)
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.headQuarter.removeObservers(viewLifecycleOwner)
    }
}