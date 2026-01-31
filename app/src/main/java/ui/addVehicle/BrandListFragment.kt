package ui.addVehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.vehicle.R
import com.app.vehicle.databinding.FragmentBrandListBinding

class BrandListFragment(
    private val onBrandSelected: (Brand) -> Unit
) : Fragment() {

    private var _binding: FragmentBrandListBinding? = null
    private val binding get() = _binding!!

    // List of brands with logos
    private val brandList = listOf(
        Brand("Honda", R.drawable.honda),
        Brand("Toyota", R.drawable.yamaha),
        Brand("Maruti", R.drawable.tata),
        Brand("Ford", R.drawable.bajaj),
        Brand("Hyundai", R.drawable.hero)
    )

    private lateinit var adapter: BrandAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize adapter
        adapter = BrandAdapter(brandList) { selectedBrand ->
            // Callback to AddVehicleFragment
            onBrandSelected(selectedBrand)
            //parentFragmentManager.popBackStack()
        }

        binding.rvBrands.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBrands.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
