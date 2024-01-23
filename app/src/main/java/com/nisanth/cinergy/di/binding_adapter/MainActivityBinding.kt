package com.nisanth.cinergy.di.binding_adapter

import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.findNavController
import com.nisanth.cinergy.R
import com.nisanth.cinergy.di.hide
import com.nisanth.cinergy.di.show

object MainActivityBinding {

    @JvmStatic
    @BindingAdapter("handleToolbarVisibility")
    fun FragmentContainerView.handleToolbarVisibility(toolbar: View) {
        findNavController().addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.landingFragment -> toolbar.hide()
                else -> toolbar.show()
            }
        }
    }

}