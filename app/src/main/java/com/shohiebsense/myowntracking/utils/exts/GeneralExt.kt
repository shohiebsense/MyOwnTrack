package com.shohiebsense.myowntracking.utils.exts

import java.text.SimpleDateFormat
import java.util.*

fun Date.toReadableString(): String {
    val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("id"))
    return sdf.format(this)
}