package com.nisanth.cinergy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.nisanth.cinergy.data.api.response.EscapeRoomsMovies
import com.nisanth.cinergy.databinding.FragmentBookingBinding
import com.nisanth.cinergy.di.showToast
import com.nisanth.cinergy.di.utility.BundleConstants
import com.nisanth.cinergy.di.utility.BundleConstants.MOVIE_ID
import com.nisanth.cinergy.di.utility.CommonAdapter
import com.nisanth.cinergy.ui.MainActivity
import com.nisanth.cinergy.ui.escape_room.EscapeRoomViewModel
import kotlinx.coroutines.launch

class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private val escapeRoomViewModel: EscapeRoomViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookingBinding.inflate(inflater, container, false).apply {
            viewModel = escapeRoomViewModel
            dateAdapter = CommonAdapter(mutableListOf<EscapeRoomsMovies>(), R.layout.layout_date_item, escapeRoomViewModel.showDateTimeListener)
            timeAdapter = CommonAdapter(mutableListOf<EscapeRoomsMovies>(), R.layout.layout_time_item, null)
            lifecycleOwner = viewLifecycleOwner
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(MOVIE_ID, "")?.let { escapeRoomViewModel.getMovieInfo(it) }
        listenForProgress()
        listenForToast()
    }

    private fun listenForProgress() {  //  We can create a base fragment abstract class for this common section if needed
        lifecycleScope.launch {
            escapeRoomViewModel.showProgress.collect {
                if (it)
                    (requireActivity() as MainActivity).showProgress()
                else
                    (requireActivity() as MainActivity).hideProgress()
            }
        }
    }

    private fun listenForToast() {
        lifecycleScope.launch {
            escapeRoomViewModel.showToast.collect {
                it?.let {
                    context?.showToast(it)
                }
            }
        }
    }

}