package com.example.shoppinglist.ui.itemedit

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.example.shoppinglist.utils.toIntOrZero

@BindingAdapter("quantity")
fun setQuantity(view: EditText, newValue: Int?) {
    newValue?.let {
        if (view.text.toString().toIntOrZero() != newValue) {
            view.setText(it.toString())
        }
    }
}

@InverseBindingAdapter(attribute = "quantity")
fun getQuantity(view: EditText): Int {
    return view.text.toString().toIntOrZero()
}

@BindingAdapter("quantityAttrChanged")
fun setQuantityListeners(
    view: EditText,
    attrChange: InverseBindingListener
) {
    view.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            attrChange.onChange()
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}