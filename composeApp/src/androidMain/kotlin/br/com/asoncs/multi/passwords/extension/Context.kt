package br.com.asoncs.multi.passwords.extension

import android.content.Context
import android.widget.Toast

fun Context.showToast(
    message: String,
    isShortDuration: Boolean = true
) {
    Toast.makeText(
        this,
        message,
        if (isShortDuration)
            Toast.LENGTH_SHORT
        else
            Toast.LENGTH_LONG
    ).show()
}
