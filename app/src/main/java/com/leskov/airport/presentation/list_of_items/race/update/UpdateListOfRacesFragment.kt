package com.leskov.airport.presentation.list_of_items.race.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfRacesBinding
import com.leskov.airport.domain.entity.RaceEntity
import com.leskov.airport.presentation.list_of_items.race.ListOfRacesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfRacesFragment :
    BaseVMFragment<ListOfRacesViewModel, FragmentUpdateListOfRacesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_races

    override val viewModel: ListOfRacesViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getString("key") ?: "")

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }

        binding.btnConfirm.title.setText(R.string.update)
        binding.btnConfirm.crRoot.setOnClickWithDebounce {
            viewModel.updateItem(
                item = RaceEntity(
                    typeOfRace = arguments?.getString("key") ?: "",
                    numberOfRace = binding.nameOf.text.toString().toInt(),
                    timeOfDeparture = binding.officeLocation.text.toString(),
                    arrivalTime = binding.typeOf.text.toString(),
                    flightTime = binding.contactNumber.text.toString()
                )
            )
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.race.eventObserve(viewLifecycleOwner) {
            binding.race = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.race.removeObservers(viewLifecycleOwner)
    }
}