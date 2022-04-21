package com.leskov.airport.base.extensions

import android.R
import android.app.AlertDialog
import android.content.Context
import android.os.SystemClock
import android.text.TextUtils
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText


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

fun Fragment.showMessage(text: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, duration).show()
}

fun Fragment.showMessage(@StringRes text: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), text, duration).show()
}

fun Fragment.isInputsFilled(vararg editText: TextInputEditText): Boolean {
    var result = false

    for (text in editText) {
        if (TextUtils.isEmpty(text.text.toString())) {
            val focusView: View = text
            text.error = getString(com.leskov.airport.R.string.error_required)
            focusView.requestFocus()
            result = true
        }

    }
    return result
}

fun Fragment.showSnackbarWithRemove(
    description: String,
    duration: Int = Snackbar.LENGTH_SHORT,
    onComplete: () -> Unit
) {
    Snackbar.make(requireView(), description, duration)
        .addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
            override fun onShown(transientBottomBar: Snackbar?) {
                super.onShown(transientBottomBar)
                onComplete.invoke()
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {}
        }).show()
}

fun Fragment.showAlertDialog(title: String, content: String, listener: (() -> Unit)? = null) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(content)
        .setNegativeButton("Cancel", null)
        .setPositiveButton("Confirm") { _, _ -> listener?.invoke() }
        .create()
        .show()
}

fun Fragment.showAlertDialogWithList(title: String, array: Array<String>, action: (Int) -> Unit) {
    MaterialAlertDialogBuilder(requireContext())
        .setTitle(title)
        .setItems(array) { dialog, which ->
            action.invoke(which)
            dialog.dismiss()
        }
        .setNegativeButton("Cancel", null)
        .show()
}

fun Fragment.initSelectedTypeList(autoCompleteTextView: AutoCompleteTextView, list: List<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, list)
    autoCompleteTextView.setAdapter(adapter)
}

fun Fragment.hideKeyboard(view: View) {
    val imm: InputMethodManager? =
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view.windowToken, 0)
}

fun SwipeRefreshLayout.showRefresh() {
    this.updateVisibility(true)
}

fun SwipeRefreshLayout.hideRefresh() {
    this.updateVisibility(false)
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
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
