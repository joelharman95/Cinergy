package com.nisanth.cinergy.di.binding_adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nisanth.cinergy.data.api.response.EscapeRoomsMovies
import com.nisanth.cinergy.di.loadImageFromUrl
import com.nisanth.cinergy.di.utility.CommonAdapter

object EscapeRoomBinding {

    @JvmStatic
    @BindingAdapter("setEscapeRoomAdapter", "setEscapeRooms")
    fun <T, A> RecyclerView.setEscapeRoomAdapter(escapeRoomAdapter: CommonAdapter<T, A>?, escapeRooms: List<EscapeRoomsMovies>) {
        if (escapeRoomAdapter != null) {
            if (adapter == null) {
                adapter = escapeRoomAdapter
            }
            escapeRoomAdapter.addList(escapeRooms as List<T>)
        }
    }

    @JvmStatic
    @BindingAdapter("setEscapeRoomPic")
    fun ImageView.setEscapeRoomPic(url: String?) {
        context.loadImageFromUrl(this, url)
    }

}