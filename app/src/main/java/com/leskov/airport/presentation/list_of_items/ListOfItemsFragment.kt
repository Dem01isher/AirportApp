package com.leskov.airport.presentation.list_of_items

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.SwipeToDeleteCallback
import com.leskov.airport.base.utils.SwipeToUpdateCallback
import com.leskov.airport.databinding.FragmentListOfItemsBinding
import com.leskov.airport.domain.entity.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val listOfItemsAdapter = ListOfItemsAdapter()

    private val searchListOfItemsAdapter = SearchListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        viewModel.setType(arguments?.getString("title") ?: "")

        binding.toolbarTitle.text = getString(arguments?.getInt("type") ?: 0)

        binding.rvListOfItems.adapter = listOfItemsAdapter

        binding.rvSearchList.adapter = searchListOfItemsAdapter

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                val adapter = binding.rvListOfItems.adapter as ListOfItemsAdapter

                showAlertDialog(
                    getString(R.string.remove_item),
                    getString(R.string.you_sure_u_want_remove_item),
                    negativeAction = { adapter.notifyItemChanged(viewHolder.bindingAdapterPosition) },
                    positiveAction = {
                        viewModel.removeItemByType(adapter.currentList[viewHolder.bindingAdapterPosition])
                    }
                )
            }
        }

        val swipeRightHandler = object : SwipeToUpdateCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (arguments?.getString("title")) {
                    TypeOfEntity.AIRPORT -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as AirportEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf(
                                "key" to item.countryLocation,
                                "type" to arguments?.getInt("type")
                            )
                        )
                    }
                    TypeOfEntity.AIRCOMPANY -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as AirCompanyEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf(
                                "key" to item.officeLocation,
                                "type" to arguments?.getInt("type")
                            )
                        )
                    }
                    TypeOfEntity.HEADQUARTERS -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as HeadQuarterEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf("key" to item.numberOf, "type" to arguments?.getInt("type"))
                        )
                    }
                    TypeOfEntity.ROUTE -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as RouteEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf(
                                "key" to item.departureCountry,
                                "type" to arguments?.getInt("type")
                            )
                        )
                    }
                    TypeOfEntity.INSURANCE -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as InsuranceEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf("key" to item.serviceName, "type" to arguments?.getInt("type"))
                        )
                    }
                    TypeOfEntity.TEAM -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as TeamEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf(
                                "key" to item.countOfPeople,
                                "type" to arguments?.getInt("type")
                            )
                        )
                    }
                    TypeOfEntity.RACE -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as RaceEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf("key" to item.typeOfRace, "type" to arguments?.getInt("type"))
                        )
                    }
                    TypeOfEntity.AIRPLANE -> {
                        val item =
                            listOfItemsAdapter.currentList[viewHolder.bindingAdapterPosition] as AirplaneEntity
                        navController.navigate(
                            R.id.action_listOfItemsFragment_to_updateItemFragment,
                            bundleOf("key" to item.producer, "type" to arguments?.getInt("type"))
                        )
                    }
                }
            }
        }

        ItemTouchHelper(swipeHandler).attachToRecyclerView(binding.rvListOfItems)
        ItemTouchHelper(swipeRightHandler).attachToRecyclerView(binding.rvListOfItems)

        binding.searchField.onSearchQueryListener { searchQuery ->
            viewModel.searchDataByType(searchQuery)
        }

        binding.flAddItem.setOnClickWithDebounce {
            navController.navigate(
                R.id.action_listOfItemsFragment_to_insertNewItemFragment,
                bundleOf("type" to arguments?.getString("title"))
            )
        }

        binding.btnMore.setOnClickWithDebounce {
            showAlertDialogWithList(getString(R.string.choose_table), listOfTypes) {
                viewModel.setType(listOfTypes[it])
                viewModel.fetchSelectedTypeData()
                showMessage(
                    String.format(
                        getString(R.string.current_list_switched),
                        listOfTypes[it]
                    )
                )
            }
        }

        binding.btnSearch.setOnClickWithDebounce {
            openSearch()
        }

        binding.ivCancelSearch.setOnClickWithDebounce {
            cancelSearch()
        }

        binding.arrowBack.setOnClickWithDebounce {
            navController.popBackStack()
        }
    }

    override fun initObservers() {
        super.initObservers()
        viewModel.listOfData.nonNullObserve(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) {
                binding.listStateLayout.updateVisibility(false)
                listOfItemsAdapter.submitList(list as MutableList<Any?>)
            } else {
                listOfItemsAdapter.currentList.clear()
                binding.listStateLayout.updateVisibility(true)
                binding.progressLayout.root.updateVisibility(false)
            }
        }
        viewModel.loading.eventObserve(viewLifecycleOwner) { loading ->
            binding.progressLayout.root.updateVisibility(loading)
        }

        viewModel.updatedList.nonNullObserve(viewLifecycleOwner) { list ->
            searchListOfItemsAdapter.submitList(list as MutableList<Any?>)
        }

        viewModel.updateListState.nonNullObserve(viewLifecycleOwner) {
            binding.listStateLayout.updateVisibility(it)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchSelectedTypeData()
    }

    private fun checkList(list: MutableList<Any?>, isEmpty: Boolean) {
        if (isEmpty) {
            searchListOfItemsAdapter.currentList.clear()
            binding.listStateLayout.updateVisibility(isEmpty)
        } else {
            binding.listStateLayout.updateVisibility(isEmpty)
            searchListOfItemsAdapter.submitList(list)
        }
    }

    private fun openSearch() {
        binding.listStateLayout.updateVisibility(false)
        binding.rvSearchList.updateVisibility(true)
        binding.rvListOfItems.updateVisibility(false)
        binding.searchField.updateVisibility(true)
        binding.searchField.showKeyboard(requireActivity())
        binding.searchField.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(true)
        binding.arrowBack.updateVisibility(false)
        binding.btnSearch.updateVisibility(false)
        binding.toolbarTitle.updateVisibility(false)
        binding.btnMore.updateVisibility(false)
    }

    override fun onPause() {
        super.onPause()
        viewModel.updatedList.removeObservers(viewLifecycleOwner)
        viewModel.listOfData.removeObservers(viewLifecycleOwner)
        viewModel.updateListState.removeObservers(viewLifecycleOwner)
        viewModel.loading.removeObservers(viewLifecycleOwner)
        cancelSearch()
    }

    private fun cancelSearch() {
        binding.rvSearchList.updateVisibility(false)
        binding.rvListOfItems.updateVisibility(true)
        binding.searchField.updateVisibility(false)
        binding.searchField.clearFocus()
        binding.searchField.hideKeyboard(requireActivity())
        binding.searchField.text.clear()
        binding.btnMore.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(false)
        binding.searchField.updateVisibility(false)
        binding.arrowBack.updateVisibility(true)
        binding.btnSearch.updateVisibility(true)
        binding.toolbarTitle.updateVisibility(true)
    }
}