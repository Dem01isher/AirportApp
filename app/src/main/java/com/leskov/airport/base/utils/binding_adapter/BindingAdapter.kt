package com.leskov.airport.base.utils.binding_adapter

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibility")
fun updateVisibility(view: View, isVisible: Boolean){
    if (isVisible) view.visibility = View.VISIBLE else view.visibility = View.GONE
}