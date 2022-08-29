package com.example.spacextestapp.presentation.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.example.spacextestapp.R
import com.example.spacextestapp.SpacexApp
import com.example.spacextestapp.databinding.FragmentDetailMissionBinding
import com.example.spacextestapp.di.DaggerActivityViewModelComponent
import com.example.spacextestapp.domain.model.DetailLaunchData
import com.example.spacextestapp.ext.toLaunchResultColorString
import com.example.spacextestapp.ext.toLaunchResultString
import com.example.spacextestapp.presentation.LaunchesViewModel
import com.example.spacextestapp.presentation.detail.adapters.CrewAdapter
import com.example.spacextestapp.presentation.list.LaunchesListEvent
import com.example.spacextestapp.presentation.list.adapters.AdapterActionListener
import com.example.spacextestapp.presentation.list.adapters.LaunchesAdapter
import com.example.spacextestapp.presentation.list.adapters.LaunchesStateAdapter
import javax.inject.Inject

class MissionDetailFragment : Fragment() {

    private var _binding: FragmentDetailMissionBinding? = null
    private val mBinding get() = _binding!!

    @Inject
    lateinit var viewModel: LaunchesViewModel

    private var args: DetailLaunchData? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val component =
            DaggerActivityViewModelComponent
                .builder()
                .componentActivity(requireActivity())
                .appComponent((requireActivity().applicationContext as SpacexApp).appComponent)
                .build()
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args =
            arguments?.getParcelable("detail") ?: DetailLaunchData(crew = listOf(), imageUrl = "")
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMissionBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCrewList()

        with(mBinding) {

            launchDetailIV.load(args?.imageUrl) {
                crossfade(true)
                error(R.drawable.ic_image_error_place_holder)
                placeholder(R.drawable.ic_image_load_place_holder)
            }
            missionNameTV.text = requireContext().resources.getString(R.string.mission_name, args?.launchName )
            flightCountTV.text = requireContext().resources.getString(R.string.flight_count, args?.flight.toString())
            missionStatusTV.text = args?.launchSuccess?.toLaunchResultString()?.toLaunchResultColorString(requireContext())
            descriptionTV.text = args?.description
            missionDateTimeTV.text = args?.date
        }
    }

    private fun setupCrewList() {
        if (args?.crew?.isNotEmpty() == true) {
            val adapter = CrewAdapter(requireContext(), args?.crew ?: listOf())
            mBinding.crewRV.layoutManager = LinearLayoutManager(requireContext())
            mBinding.crewRV.adapter = adapter
        } else {
            mBinding.crewRV.visibility = View.GONE
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}