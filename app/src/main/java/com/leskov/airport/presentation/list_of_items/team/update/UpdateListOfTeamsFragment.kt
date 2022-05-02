package com.leskov.airport.presentation.list_of_items.team.update

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.eventObserve
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentUpdateListOfTeamsBinding
import com.leskov.airport.domain.entity.TeamEntity
import com.leskov.airport.presentation.list_of_items.team.ListOfTeamsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateListOfTeamsFragment :
    BaseVMFragment<ListOfTeamsViewModel, FragmentUpdateListOfTeamsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_update_list_of_teams

    override val viewModel: ListOfTeamsViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        viewModel.findItemByKey(arguments?.getInt("key") ?: 0)

        binding.apply {
            btnConfirm.title.setText(R.string.update)
            btnConfirm.crRoot.setOnClickWithDebounce {
                viewModel.updateItem(
                    item = TeamEntity(
                        numberOf = binding.nameOf.text.toString().toInt(),
                        countOfEngineers = officeLocation.text.toString().toInt(),
                        numberOfMovers = typeOf.text.toString().toInt(),
                        countOfPilots = contactNumber.text.toString().toInt(),
                        countFlightAttendants = dateOfFoundation.text.toString().toInt(),
                        countOfPeople = arguments?.getInt("key") ?: 0
                    )
                )
                navController.popBackStack()
            }
            toolbar.setNavigationOnClickListener { navController.popBackStack() }
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.team.eventObserve(viewLifecycleOwner) {
            binding.team = it
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.team.removeObservers(viewLifecycleOwner)
    }
}