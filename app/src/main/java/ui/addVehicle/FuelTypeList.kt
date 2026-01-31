package ui.addVehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.vehicle.databinding.FragmentBrandListBinding
import com.app.vehicle.databinding.ItemFuelBinding

class FuelTypeListFragment : Fragment() {

    private var _binding: FragmentBrandListBinding? = null
    private val binding get() = _binding!!

    private val fuels = listOf(
        FuelType("Petrol"),
        FuelType("Diesel"),
        FuelType("Electric"),
        FuelType("CNG")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvBrands.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBrands.adapter = FuelTypeAdapter(fuels) { fuel ->

            parentFragmentManager.setFragmentResult(
                "fuel_result",
                Bundle().apply {
                    putString("fuel_name", fuel.name)
                }
            )

            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
