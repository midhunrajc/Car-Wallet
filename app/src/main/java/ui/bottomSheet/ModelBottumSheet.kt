package com.app.vehicle.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.vehicle.databinding.BsSelectListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ModelBottomSheet(
    private val brand: String,
    private val onModelSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: BsSelectListBinding

    private val modelsByBrand = mapOf(
        "Honda" to listOf("City", "Amaze", "Civic"),
        "Hyundai" to listOf("i10", "i20", "Creta"),
        "Tata" to listOf("Nexon", "Punch", "Harrier"),
        "Mahindra" to listOf("XUV300", "XUV700"),
        "Suzuki" to listOf("Swift", "Baleno"),
        "Toyota" to listOf("Innova", "Fortuner")
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

        binding.tvTitle.text = "Select Model"

        val models = modelsByBrand[brand].orEmpty()

        models.forEach { model ->
            binding.container.addView(
                createItemView(model) {
                    onModelSelected(model)
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
