package com.leskov.airport.base.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.leskov.airport.base.view_model.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint


abstract class BaseVMFragment<VM: BaseViewModel, Binding: ViewDataBinding> : BaseBindingFragment<Binding>() {

    protected abstract val viewModel: VM

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        initObservers()
    }

    protected open fun initListeners() {}

    protected open fun initObservers(){}
}