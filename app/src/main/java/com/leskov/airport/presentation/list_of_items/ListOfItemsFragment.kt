package com.leskov.airport.presentation.list_of_items

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.extensions.*
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentListOfItemsBinding
import com.leskov.airport.domain.entity.listOfTypes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfItemsFragment : BaseVMFragment<ListOfItemsViewModel, FragmentListOfItemsBinding>() {

    override val layoutId: Int = R.layout.fragment_list_of_items

    override val viewModel: ListOfItemsViewModel by viewModels()

    private val listOfItemsAdapter = ListOfItemsAdapter()

    override fun initListeners() {
        super.initListeners()

        viewModel.setType(arguments?.getString("title") ?: "")

        binding.toolbarTitle.text = arguments?.getString("title") ?: ""

        binding.rvListOfItems.adapter = listOfItemsAdapter

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
        viewModel.listOfData.eventObserve(viewLifecycleOwner) { list ->
            listOfItemsAdapter.currentList = list as MutableList<Any?>
            listOfItemsAdapter.notifyDataSetChanged()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchSelectedTypeData()
    }

    private fun openSearch() {
        binding.searchField.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(true)
        binding.arrowBack.updateVisibility(false)
        binding.btnSearch.updateVisibility(false)
        binding.toolbarTitle.updateVisibility(false)
        binding.btnMore.updateVisibility(false)
    }

    private fun cancelSearch() {
        binding.searchField.clearFocus()
        hideKeyboard(binding.searchField)
        binding.searchField.text.clear()
        binding.btnMore.updateVisibility(true)
        binding.ivCancelSearch.updateVisibility(false)
        binding.searchField.updateVisibility(false)
        binding.arrowBack.updateVisibility(true)
        binding.btnSearch.updateVisibility(true)
        binding.toolbarTitle.updateVisibility(true)
    }
}