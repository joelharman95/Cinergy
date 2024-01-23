package com.nisanth.cinergy.di.utility

import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.inSpans
import com.nisanth.cinergy.R
import com.nisanth.cinergy.di.getCompatColor

fun TextView.applySpanPo(stringPrefix: String, stringSuffix: String) {
    text = buildSpannedString {
        inSpans {
            ForegroundColorSpan(getCompatColor(R.color.greenish_500))
        }
        inSpans {
            bold { append(stringPrefix) }
        }.inSpans(
            ForegroundColorSpan(getCompatColor(R.color.black))
        ) {
            append(stringSuffix)
        }
    }
}