package ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.vehicle.R
import com.app.vehicle.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.collectLatest

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()
    private val adapter = VehicleAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        setupRecyclerView()
        observeUiState()

        binding.fabAddVehicle.setOnClickListener {
            // navigate to AddVehicleFragment
        }

        binding.btnFilter.setOnClickListener {
            // open filter bottom sheet
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerVehicles.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.uiState.collectLatest { state ->
                binding.progressBar.visibility =
                    if (state.isLoading) View.VISIBLE else View.GONE

                adapter.submitList(state.vehicles)
            }
        }
    }
}
