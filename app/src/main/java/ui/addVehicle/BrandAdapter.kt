package ui.addVehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.R
import com.app.vehicle.databinding.ItemBrandBinding

data class Brand(
    val name: String,
    val logoRes: Int
)

class BrandAdapter(
    private val brands: List<Brand>,
    private val onClick: (Brand) -> Unit
) : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    private var selectedPosition = -1 // track which brand is selected

    inner class BrandViewHolder(val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: Brand, position: Int) {
            binding.tvBrand.text = brand.name
            binding.ivBrandLogo.setImageResource(brand.logoRes)

            // Update radio button state
            binding.radioBrand.isChecked = position == selectedPosition

            // Click on entire row
            binding.root.setOnClickListener {
                updateSelection(position, brand)
            }

            // Click on radio button itself
            binding.radioBrand.setOnClickListener {
                updateSelection(position, brand)
            }
        }

        private fun updateSelection(position: Int, brand: Brand) {
            // Notify previous selected to uncheck
            val previousPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)

            // Callback to fragment
            onClick(brand)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount() = brands.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(brands[position], position)
    }
}

