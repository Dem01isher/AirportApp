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

import android.annotation.SuppressLint
import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.annotation.IdRes


fun View.updateVisibility(isVisible: Boolean) {
    if (isVisible) this.visibility = View.VISIBLE else this.visibility = View.GONE
}

fun View.setOnClickWithDebounce(debounceTime: Long = 600L, action: (View) -> Unit) {

    this.setOnClickListener(object : View.OnClickListener {
        private var lastClickTime: Long = 0

        override fun onClick(v: View) {
            if (SystemClock.elapsedRealtime() - lastClickTime < debounceTime) return
            else action.invoke(v)
            lastClickTime = SystemClock.elapsedRealtime()
        }
    })
}

fun EditText.showKeyboard(activity: Activity){
    val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Fragment.hideKeyboard() {
    val view = requireActivity().currentFocus
    val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManager?.hideSoftInputFromWindow(view?.windowToken, 0)
    view?.clearFocus()
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
            }

            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) { onComplete.invoke() }
        }).show()
}

fun Fragment.showAlertDialog(title: String, content: String, negativeAction: () -> Unit, positiveAction: () -> Unit) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(content)
        .setNegativeButton(getString(com.leskov.airport.R.string.cancel)) { _, _ -> negativeAction.invoke() }
        .setPositiveButton(getString(com.leskov.airport.R.string.confirm)) { _, _ -> positiveAction.invoke() }
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
        .setNegativeButton(getString(com.leskov.airport.R.string.cancel), null)
        .show()
}

@SuppressLint("ResourceType")
fun Fragment.showAlertDialogWithList(
    title: String,
    list: Array<String>,
    @IdRes icon: Int,
    action: (Int) -> Unit
) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setIcon(icon)
        .setItems(list) { dialog, position ->
            action.invoke(position)
            dialog.dismiss()
        }
        .setNegativeButton(getString(com.leskov.airport.R.string.cancel), null)
        .create()
        .show()
}

fun Fragment.initSelectedTypeList(autoCompleteTextView: AutoCompleteTextView, list: List<String>) {
    val adapter = ArrayAdapter(requireContext(), R.layout.simple_dropdown_item_1line, list)
    autoCompleteTextView.setAdapter(adapter)
}

fun EditText.hideKeyboard(activity: Activity) {
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm!!.hideSoftInputFromWindow(windowToken, 0)
}

fun EditText.onSearchQueryListener(listener: (String) -> Unit){
    addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0 != null) listener.invoke(p0.toString())
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (p0 != null) listener.invoke(p0.toString())
        }

        override fun afterTextChanged(p0: Editable?) { listener.invoke(p0.toString()) }
    })
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
