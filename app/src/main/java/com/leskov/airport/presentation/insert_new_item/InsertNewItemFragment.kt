package com.leskov.airport.presentation.insert_new_item

import androidx.fragment.app.viewModels
import com.leskov.airport.R
import com.leskov.airport.base.fragment.BaseVMFragment
import com.leskov.airport.databinding.FragmentInsertNewItemBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsertNewItemFragment :
    BaseVMFragment<InsertNewItemViewModel, FragmentInsertNewItemBinding>() {

    override val layoutId: Int = R.layout.fragment_insert_new_item

    override val viewModel: InsertNewItemViewModel by viewModels()

}