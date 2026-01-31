package ui.addVehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.ItemBrandBinding

class BrandAdapter(
    private val brands: List<String>,
    private val onClick: (String) -> Unit
) : RecyclerView.Adapter<BrandAdapter.BrandViewHolder>() {

    inner class BrandViewHolder(val binding: ItemBrandBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(brand: String) {
            binding.tvBrand.text = "• $brand"
            binding.root.setOnClickListener { onClick(brand) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val binding = ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BrandViewHolder(binding)
    }

    override fun getItemCount() = brands.size

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        holder.bind(brands[position])
    }
}
