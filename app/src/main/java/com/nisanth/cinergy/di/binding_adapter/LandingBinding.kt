package com.nisanth.cinergy.di.binding_adapter

import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nisanth.cinergy.di.utility.CommonAdapter

object LandingBinding {

    @JvmStatic
    @BindingAdapter("setBenefitsAdapter", "setBenefits")
    fun <T, A> RecyclerView.setBenefitsAdapter(benefitAdapter: CommonAdapter<T, A>?, setBenefits: MutableList<String>) {
        if (benefitAdapter != null) {
            if (adapter == null) {  //  Whenever, theres a change in list, this binding adapter will gets called everytime
                adapter = benefitAdapter
            }
            benefitAdapter.addList(setBenefits as MutableList<T>)
        }
    }

}