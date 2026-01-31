package com.app.vehicle.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.vehicle.databinding.BsSelectListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class FuelTypeBottomSheet(
    private val onFuelSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: BsSelectListBinding

    private val fuelTypes = listOf(
        "Petrol",
        "Diesel",
        "Electric",
        "CNG"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BsSelectListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTitle.text = "Select Fuel Type"

        fuelTypes.forEach { fuel ->
            binding.container.addView(
                createItemView(fuel) {
                    onFuelSelected(fuel)
                    dismiss()
                }
            )
        }
    }

    private fun createItemView(text: String, onClick: () -> Unit): View {
        val item = layoutInflater.inflate(
            android.R.layout.simple_list_item_1,
            binding.container,
            false
        )
        item.findViewById<android.widget.TextView>(android.R.id.text1).text = text
        item.setOnClickListener { onClick() }
        return item
    }
}
