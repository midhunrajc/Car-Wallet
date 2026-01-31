package ui.addVehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.FragmentBrandListBinding

class BrandListFragment(
    private val onBrandSelected: (String) -> Unit
) : Fragment() {

    private var _binding: FragmentBrandListBinding? = null
    private val binding get() = _binding!!

    private val brands = listOf("Honda", "Toyota", "Maruti", "Ford", "Hyundai")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBrandListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = BrandAdapter(brands) { brand ->
            onBrandSelected(brand)
            parentFragmentManager.popBackStack()
        }

        binding.rvBrands.layoutManager = LinearLayoutManager(requireContext())
        binding.rvBrands.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
