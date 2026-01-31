package com.app.vehicle.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.vehicle.databinding.BsSelectListBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BrandBottomSheet(
    private val onBrandSelected: (String) -> Unit
) : BottomSheetDialogFragment() {

    private lateinit var binding: BsSelectListBinding

    private val brands = listOf(
        "Honda",
        "Hyundai",
        "Tata",
        "Mahindra",
        "Suzuki",
        "Toyota"
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

        binding.tvTitle.text = "Select Brand"

        brands.forEach { brand ->
            binding.container.addView(
                createItemView(brand) {
                    onBrandSelected(brand)
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
