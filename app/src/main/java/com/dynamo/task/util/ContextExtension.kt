package com.dynamo.task.util

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast

fun Context.showToast(@StringRes textRes: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textRes, duration).show()
}

fun Context.showToast(textRes: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, textRes, duration).show()
}