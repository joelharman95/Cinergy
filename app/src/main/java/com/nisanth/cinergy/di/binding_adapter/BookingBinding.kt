package com.nisanth.cinergy.di.binding_adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nisanth.cinergy.data.api.response.Sessions
import com.nisanth.cinergy.data.api.response.ShowDateWithTimes
import com.nisanth.cinergy.di.formatDate
import com.nisanth.cinergy.di.utility.CommonAdapter
import com.nisanth.cinergy.di.utility.applySpanPo

object BookingBinding {

    @JvmStatic
    @BindingAdapter("setDateAdapter", "setDate")
    fun <T, A> RecyclerView.setDateAdapter(dateAdapter: CommonAdapter<T, A>?, showDateWithTimes: List<ShowDateWithTimes>) {
        if (dateAdapter != null) {
            if (adapter == null) {
                adapter = dateAdapter
            }
            dateAdapter.addList(showDateWithTimes as List<T>)
        }
    }

    @JvmStatic
    @BindingAdapter("setTimeAdapter", "setTime")
    fun <T, A> RecyclerView.setTimeAdapter(timeAdapter: CommonAdapter<T, A>?, time: List<Sessions>) {
        if (timeAdapter != null) {
            if (adapter == null) {
                adapter = timeAdapter
            }
            timeAdapter.addList(time as List<T>)
        }
    }

    @JvmStatic
    @BindingAdapter("bindDate")
    fun TextView.bindDate(date: String) {
        val pair: Pair<String, String> = formatDate(date)
        applySpanPo(pair.first, pair.second)
    }

}