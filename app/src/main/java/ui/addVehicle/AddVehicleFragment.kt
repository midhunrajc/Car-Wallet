package ui.addVehicle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener(
            "brand_result",
            viewLifecycleOwner
        ) { _, bundle ->
            val brandName = bundle.getString("brand_name") ?: return@setFragmentResultListener
            binding.etBrand.setText(brandName)
            viewModel.updateBrand(brandName)
        }

        binding.etBrand.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, BrandListFragment())
                .addToBackStack(null)
                .commit()
        }

        parentFragmentManager.setFragmentResultListener(
            "model_result",
            viewLifecycleOwner
        ) { _, bundle ->
            val model = bundle.getString("model_name") ?: return@setFragmentResultListener

            binding.etModel.setText(model)
            viewModel.updateModel(model)
        }

        binding.etModel.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ModelListFragment())
                .addToBackStack(null)
                .commit()
        }

        parentFragmentManager.setFragmentResultListener(
            "fuel_result",
            viewLifecycleOwner
        ) { _, bundle ->
            val fuel = bundle.getString("fuel_name") ?: return@setFragmentResultListener
            binding.etFuel.setText(fuel)
            viewModel.updateFuelType(fuel)
        }


        binding.etFuel.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, FuelTypeListFragment())
                .addToBackStack(null)
                .commit()
        }


        binding.btnSave.setOnClickListener {
            viewModel.updateVehicleNumber(binding.etVehicleNumber.text.toString())
            viewModel.updateOwnerName(binding.etOwner.text.toString())
            viewModel.saveVehicle()

            Toast.makeText(requireContext(), "Vehicle Added", Toast.LENGTH_SHORT).show()

            parentFragmentManager.popBackStack()
            (activity as MainActivity).binding.fragmentContainer.visibility = View.GONE
        }

        binding.btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
            (activity as MainActivity).binding.fragmentContainer.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
