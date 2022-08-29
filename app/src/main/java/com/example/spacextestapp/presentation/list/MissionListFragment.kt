package com.example.spacextestapp.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacextestapp.R
import com.example.spacextestapp.SpacexApp
import com.example.spacextestapp.databinding.FragmentListMissionBinding
import com.example.spacextestapp.di.DaggerActivityViewModelComponent
import com.example.spacextestapp.domain.model.DetailLaunchData
import com.example.spacextestapp.ext.navigateEvent
import com.example.spacextestapp.presentation.LaunchesViewModel
import com.example.spacextestapp.presentation.list.adapters.AdapterActionListener
import com.example.spacextestapp.presentation.list.adapters.LaunchesAdapter
import com.example.spacextestapp.presentation.list.adapters.LaunchesStateAdapter
import com.example.spacextestapp.presentation.list.adapters.TryAgainAction
import com.example.spacextestapp.util.UiEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class MissionListFragment : Fragment() {

    private var _binding: FragmentListMissionBinding? = null
    private val mBinding get() = _binding!!


    private lateinit var mainLoadStateHolder: LaunchesStateAdapter.Holder

    @Inject
    lateinit var viewModel: LaunchesViewModel

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListMissionBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupLaunchesList()
    }


    private fun setupLaunchesList() {
        val adapter = LaunchesAdapter(requireContext(),object : AdapterActionListener {
            override fun itemClick(item: DetailLaunchData) {
                val bundle = Bundle()
                bundle.putParcelable("detail", item)
                viewModel.onListEvent(LaunchesListEvent.OnLaunchesItemClick(R.id.action_missionListFragment_to_missionDetailFragment, bundle))
            }
        })

        val tryAgainAction: TryAgainAction = { adapter.retry() }

        val footerAdapter = LaunchesStateAdapter(tryAgainAction)

        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        mBinding.launchesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        mBinding.launchesRecyclerView.adapter = adapterWithLoadState
        (mBinding.launchesRecyclerView.itemAnimator as? DefaultItemAnimator)?.supportsChangeAnimations =
            false

        mainLoadStateHolder = LaunchesStateAdapter.Holder(
            mBinding.loadStateView,
            tryAgainAction
        )

        observeLaunches(adapter)
        observeLoadState(adapter)
        observeNavigate()
    }


    private fun observeLoadState(adapter: LaunchesAdapter) {
        lifecycleScope.launch {
            adapter.loadStateFlow.collectLatest { state ->
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }

    private fun observeLaunches(adapter: LaunchesAdapter) {
        lifecycleScope.launch {
            viewModel.state.collectLatest { uiState ->
                uiState.items?.collectLatest { pagingData ->
                        adapter.submitData(pagingData)
                    }
            }
        }
    }
    private fun observeNavigate() {
        lifecycleScope.launch {
            viewModel.uiEvent?.collect { event ->
                when (event) {
                    is UiEvent.Navigate -> {
                        navigateEvent(event)
                    }
                    else -> Unit
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

