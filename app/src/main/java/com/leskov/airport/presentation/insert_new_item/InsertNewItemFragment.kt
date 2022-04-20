package com.leskov.airport.presentation.insert_new_item

import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.base.extensions.setOnClickWithDebounce
import com.leskov.airport.databinding.FragmentInsertNewItemBinding
import com.leskov.airport.domain.entity.AirportEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNewItemFragment :
    BaseVMFragment<InsertNewItemViewModel, FragmentInsertNewItemBinding>() {

    override val layoutId: Int = R.layout.fragment_insert_new_item

    override val viewModel: InsertNewItemViewModel by viewModels()

    override fun initListeners() {
        super.initListeners()

        val items = listOf("Material", "Design", "Components", "Android")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, items)

        binding.tvSelectedType.setAdapter(adapter)

        binding.flConfirm.setOnClickWithDebounce {
            viewModel.insertNewItem(
                AirportEntity(
                    0,
                    title = binding.title.text.toString(),
                    type = binding.tvSelectedType.text.toString(),
                    model = binding.model.text.toString(),
                    loadCapacity = binding.capacity.text.toString()
                )
            )
            navController.popBackStack()
        }

        binding.toolbar.setNavigationOnClickListener {
            navController.popBackStack()
        }
    }
}