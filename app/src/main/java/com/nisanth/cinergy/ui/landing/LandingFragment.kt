package com.nisanth.cinergy.ui.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nisanth.cinergy.R
import com.nisanth.cinergy.databinding.FragmentLandingBinding
import com.nisanth.cinergy.di.showToast
import com.nisanth.cinergy.di.utility.BundleConstants.AUTHORIZATION
import com.nisanth.cinergy.di.utility.BundleConstants.MEMBER_ID
import com.nisanth.cinergy.di.utility.BundleConstants.USER_ID
import com.nisanth.cinergy.di.utility.CommonAdapter
import com.nisanth.cinergy.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LandingFragment : Fragment() {

    //    private val landingViewModel by viewModels<LandingViewModel>()
    private val landingViewModel: LandingViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return FragmentLandingBinding.inflate(inflater, container, false).apply {
            adpater = CommonAdapter(mutableListOf<String>(), R.layout.layout_benefits_item, null)
            viewModel = landingViewModel
            lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenForNavigation()
        listenForProgress()
        listenForToast()
    }

    /**
    * For navigating to next screen(EscapeRoom Screen)
    **/
    private fun listenForNavigation() {
        lifecycleScope.launch {
            landingViewModel.navigateToEscapeRoom.collect {
                if (it) {
                    findNavController().navigate(R.id.action_landingFragment_to_escapeRoomFragment, bundleOf(
                        MEMBER_ID to landingViewModel.user?.memberId.toString(),
                        USER_ID to landingViewModel.user?.id.toString(),
                        AUTHORIZATION to landingViewModel.authorization.toString()
                    ))
                    landingViewModel.resetNavigation()
                }
            }
        }
    }

    private fun listenForProgress() {  //  We can create a base fragment abstract class for this common section if needed
        lifecycleScope.launch {
            landingViewModel.showProgress.collect {
                if (it)
                    (requireActivity() as MainActivity).showProgress()
                else
                    (requireActivity() as MainActivity).hideProgress()
            }
        }
    }

    private fun listenForToast() {
        lifecycleScope.launch {
            landingViewModel.showToast.collect {
                it?.let {
                    context?.showToast(it)
                }
            }
        }
    }

}