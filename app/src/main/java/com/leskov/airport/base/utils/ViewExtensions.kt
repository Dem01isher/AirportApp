package com.leskov.airport.base.utils

import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


fun View.updateVisibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun View.setOnClickWithDebounce(debounceTime: Long = 600L, action: () -> Unit) {

    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action()
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun Fragment.showMessage(text: String, duration: Int){
    Toast.makeText(requireContext(), text, duration).show()
}

fun Fragment.showMessage(@StringRes text: Int, duration: Int){
    Toast.makeText(requireContext(), text, duration).show()
}

fun SwipeRefreshLayout.showRefresh() {
    this.updateVisibility(true)
}

fun SwipeRefreshLayout.hideRefresh() {
    this.updateVisibility(false)
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.disableClick() {
    isClickable = false
}

fun View.enableClick() {
    isClickable = true
}
