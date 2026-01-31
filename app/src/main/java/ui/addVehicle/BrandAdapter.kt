package ui.addVehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.ItemBrandBinding

class BrandAdapter(
    private val brands: List<Brand>,
    private val onClick: (Brand) -> Unit
) : RecyclerView.Adapter<BrandAdapter.BrandVH>() {

    private var selectedPos = -1

    inner class BrandVH(private val binding: ItemBrandBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(brand: Brand, position: Int) {
            binding.tvBrand.text = brand.name
            binding.ivBrandLogo.setImageResource(brand.logoRes)
            binding.radioBrand.isChecked = position == selectedPos

            binding.root.setOnClickListener { select(position, brand) }
            binding.radioBrand.setOnClickListener { select(position, brand) }
        }

        private fun select(pos: Int, brand: Brand) {
            val old = selectedPos
            selectedPos = pos
            if (old != -1) notifyItemChanged(old)
            notifyItemChanged(pos)
            onClick(brand)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandVH {
        return BrandVH(
            ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BrandVH, position: Int) {
        holder.bind(brands[position], position)
    }

    override fun getItemCount(): Int = brands.size
}
