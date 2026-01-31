package ui.common

import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment

// View visibility helpers
fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

// EditText text change helper
fun EditText.onTextChanged(block: (String) -> Unit) {
    this.addTextChangedListener {
        block(it.toString())
    }
}

// Toast helper
fun Fragment.showToast(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}
