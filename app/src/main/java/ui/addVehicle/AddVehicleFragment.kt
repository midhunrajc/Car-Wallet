package ui.addVehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.vehicle.MainActivity
import com.app.vehicle.R
import com.app.vehicle.databinding.FragmentAddVehicleBinding

class AddVehicleFragment : Fragment() {

    private var _binding: FragmentAddVehicleBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddVehicleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddVehicleBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val brands = listOf("Honda", "Toyota", "Maruti", "Ford", "Hyundai")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etBrand.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragmentContainer,
                    BrandListFragment { selectedBrand ->
                        binding.etBrand.setText(selectedBrand) // show selected brand
                        viewModel.updateBrand(selectedBrand)
                    })
                .addToBackStack(null)
                .commit()
        }

        binding.btnSave.setOnClickListener {
            viewModel.updateVehicleNumber(binding.etVehicleNumber.text.toString())
            viewModel.updateOwnerName(binding.tvTitle.text.toString())

            viewModel.saveVehicle()

            Toast.makeText(requireContext(), "Vehicle Added!", Toast.LENGTH_SHORT).show()
            parentFragmentManager.popBackStack()
            (activity as MainActivity).binding.fragmentContainer.visibility = View.GONE
            (activity as MainActivity).binding.btnAddVehicle.visibility = View.VISIBLE
            (activity as MainActivity).binding.rvVehicles.visibility = View.VISIBLE
            (activity as MainActivity).binding.topBlue.visibility = View.VISIBLE
            (activity as MainActivity).binding.headerRow.visibility = View.VISIBLE
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            (activity as MainActivity).binding.fragmentContainer.visibility = View.GONE
            (activity as MainActivity).binding.btnAddVehicle.visibility = View.VISIBLE
            (activity as MainActivity).binding.rvVehicles.visibility = View.VISIBLE
            (activity as MainActivity).binding.topBlue.visibility = View.VISIBLE
            (activity as MainActivity).binding.headerRow.visibility = View.VISIBLE
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
