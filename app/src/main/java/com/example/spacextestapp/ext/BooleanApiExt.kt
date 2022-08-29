package com.example.spacextestapp.ext

import android.content.Context
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import com.example.spacextestapp.R

fun Boolean.toLaunchResultString(): String {
    return if (this) "Success" else "Failure"
}

fun String.toBooleanResultString(): Boolean {
    return this == "Success"
}

fun String.toLaunchResultColorString(context: Context): SpannableStringBuilder {
    val s = this
    val text = context.resources.getString(
        R.string.launch_result, this
    )
    val start = text.indexOf(this)
    val end = start + this.length

    return SpannableStringBuilder(text).apply {
        setSpan(
            if (s.toBooleanResultString()) ForegroundColorSpan(Color.GREEN) else ForegroundColorSpan(
                Color.RED
            ),
            start,
            end,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }


}