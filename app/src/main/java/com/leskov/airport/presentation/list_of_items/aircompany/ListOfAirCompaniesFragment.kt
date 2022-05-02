package com.leskov.airport.presentation.list_of_items.aircompany

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.utils.SwipeToDeleteCallback
import com.leskov.airport.databinding.FragmentListOfAirCompaniesBinding
import com.leskov.airport.domain.entity.TypeOfEntity
import com.leskov.airport.presentation.dialog.FilterListDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfAirCompaniesFragment :
    BaseVMFragment<ListOfAirCompaniesViewModel, FragmentListOfAirCompaniesBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_list_of_air_companies

    override val viewModel: ListOfAirCompaniesViewModel by viewModels()

    private val adapter = ListOfAirCompaniesAdapter()

    override fun initListeners() {
        super.initListeners()

        binding.rvListOfItems.adapter = adapter

        binding.btnSearch.setOnClickWithDebounce { openSearch() }

        binding.ivCancelSearch.setOnClickWithDebounce { cancelSearch() }

        binding.arrowBack.setOnClickWithDebounce { navController.popBackStack() }

        binding.flAddItem.setOnClickWithDebounce {
            navController.navigate(
                R.id.action_listOfAirCompaniesFragment_to_insertNewItemFragment,
                bundleOf("type" to TypeOfEntity.AIRCOMPANY)
            )
        }

        binding.btnMore.setOnClickWithDebounce {
            FilterListDialogFragment.newInstance("Test", "test")
                .show(requireActivity().supportFragmentManager, tag)
        }

        adapter.setOnItemClickListener {
            navController.navigate(
                R.id.action_listOfAirCompaniesFragment_to_updateListOfCompaniesFragment,
                bundleOf("key" to it.nameOf)
            )
        }

        ItemTouchHelper(object : SwipeToDeleteCallback(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeItem(adapter.currentList[viewHolder.bindingAdapterPosition])
            }
        }).attachToRecyclerView(binding.rvListOfItems)

    }

    override fun initObservers() {
        super.initObservers()

        viewModel.list.nonNullObserve(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.listStateLayout.updateVisibility(true)
            } else {
                binding.listStateLayout.updateVisibility(false)
                binding.progressLayout.root.updateVisibility(false)
                adapter.submitList(it)
            }
        }

        binding.searchField.onSearchQueryListener {
            viewModel.searchData(it).nonNullObserve(viewLifecycleOwner) { list ->
                adapter.submitList(list)
            }
        }

        setFragmentResultListener("filter_request") { requestKey, bundle ->

        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.list.removeObservers(viewLifecycleOwner)
    }

    private fun openSearch() {
        binding.btnMore.updateVisibility(false)
        binding.listStateLayout.updateVisibility(false)
        binding.searchField.updateVisibility(true)
        binding.searchField.showKeyboard(requireActivity())
        binding.searchField.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(true)
        binding.arrowBack.updateVisibility(false)
        binding.btnSearch.updateVisibility(false)
        binding.toolbarTitle.updateVisibility(false)
    }

    private fun cancelSearch() {
        binding.btnMore.updateVisibility(true)
        binding.searchField.updateVisibility(false)
        binding.searchField.clearFocus()
        binding.searchField.hideKeyboard(requireActivity())
        binding.searchField.text.clear()
        binding.ivCancelSearch.updateVisibility(false)
        binding.searchField.updateVisibility(false)
        binding.arrowBack.updateVisibility(true)
        binding.btnSearch.updateVisibility(true)
        binding.toolbarTitle.updateVisibility(true)
    }
}