package com.leskov.airport.base

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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