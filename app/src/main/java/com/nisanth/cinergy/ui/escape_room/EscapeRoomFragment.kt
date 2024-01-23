package com.nisanth.cinergy.ui.escape_room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nisanth.cinergy.R
import com.nisanth.cinergy.data.api.response.EscapeRoomsMovies
import com.nisanth.cinergy.databinding.FragmentEscapeRoomBinding
import com.nisanth.cinergy.di.showToast
import com.nisanth.cinergy.di.utility.BundleConstants.AUTHORIZATION
import com.nisanth.cinergy.di.utility.BundleConstants.MEMBER_ID
import com.nisanth.cinergy.di.utility.BundleConstants.USER_ID
import com.nisanth.cinergy.di.utility.CommonAdapter
import com.nisanth.cinergy.di.utility.EscapeRoomListener
import com.nisanth.cinergy.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EscapeRoomFragment : Fragment() {

    private var _binding: FragmentEscapeRoomBinding? = null
    private val escapeRoomViewModel: EscapeRoomViewModel by activityViewModels()
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentEscapeRoomBinding.inflate(inflater, container, false).apply {
            viewModel = escapeRoomViewModel
            adpater = CommonAdapter(mutableListOf<EscapeRoomsMovies>(), R.layout.layout_escape_room_item, escapeRoomViewModel.escapeRoomListener)
            lifecycleOwner = viewLifecycleOwner
        }.also {
            //  A quick fix/containment when opening detail dialog and closing it using back button
            //  and reopening escape screen from landing(continue as guest) screen, detail dialog will popup
            escapeRoomViewModel.closeDetailDialogIfItsOpen(it.root)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getEscapeRoomMovies()
        listenForNavigation()
        listenForProgress()
        listenForToast()
    }

    private fun getEscapeRoomMovies() {
        arguments?.apply {
            escapeRoomViewModel.getEscapeRoomMovies(
                memberId = getString(MEMBER_ID, "null"),
                userId = getString(USER_ID, "11"),
                authorization = getString(AUTHORIZATION, ""))
        }
    }

    private fun listenForNavigation() {
        lifecycleScope.launch {
            escapeRoomViewModel.openDetailView.observe(viewLifecycleOwner) {
                if (it) {
                    findNavController().navigate(R.id.action_escapeRoomFragment_to_bottomSheetDialogFragment, bundleOf())
                // 1. we can either pass arguments via nav args or
                // 2. bundle or
                // 3. via shared viewModel  //  We are using this
                } else {
                    if (findNavController().currentDestination?.id == R.id.bottomSheetDialogFragment)
                        findNavController().navigateUp()
                }
            }
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}