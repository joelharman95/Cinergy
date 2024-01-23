package com.nisanth.cinergy.ui.escape_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.nisanth.cinergy.R
import com.nisanth.cinergy.databinding.FragmentEscapeRoomDetailBinding
import com.nisanth.cinergy.di.utility.BundleConstants.MOVIE_ID
import kotlinx.coroutines.launch

class EscapeRoomDetailFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentEscapeRoomDetailBinding? = null
    private val binding get() = _binding!!
    private val escapeRoomViewModel: EscapeRoomViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dialog?.setCanceledOnTouchOutside(false)
        _binding = FragmentEscapeRoomDetailBinding.inflate(inflater, container, false).apply {
            viewModel = escapeRoomViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listenForNavigation()
    }

    private fun listenForNavigation() {
        lifecycleScope.launch {
            escapeRoomViewModel.openBookingScreen.collect {
                if (it) {
                    escapeRoomViewModel.resetNavigation()
                    findNavController().navigate(
                        R.id.action_bottomSheetDialogFragment_to_bookingFragment,
                        bundleOf(MOVIE_ID to escapeRoomViewModel.escapeRoomsMovies.value?.iD)
                    )
                }
            }
        }
    }

    override fun getTheme(): Int {
        return R.style.BottomSheetMenuTheme
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}