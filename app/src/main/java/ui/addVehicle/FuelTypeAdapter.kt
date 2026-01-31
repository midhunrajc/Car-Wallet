package ui.addVehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.ItemFuelBinding

class FuelTypeAdapter(
    private val fuels: List<FuelType>,
    private val onClick: (FuelType) -> Unit
) : RecyclerView.Adapter<FuelTypeAdapter.FuelVH>() {

    private var selectedPosition = -1

    inner class FuelVH(private val binding: ItemFuelBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(fuel: FuelType, position: Int) {
            binding.tvFuel.text = fuel.name
            binding.radioFuel.isChecked = position == selectedPosition

            binding.root.setOnClickListener { select(position, fuel) }
            binding.radioFuel.setOnClickListener { select(position, fuel) }
        }

        private fun select(position: Int, fuel: FuelType) {
            val prev = selectedPosition
            selectedPosition = position
            notifyItemChanged(prev)
            notifyItemChanged(position)
            onClick(fuel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FuelVH {
        val binding =
            ItemFuelBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FuelVH(binding)
    }

    override fun getItemCount() = fuels.size

    override fun onBindViewHolder(holder: FuelVH, position: Int) {
        holder.bind(fuels[position], position)
    }
}
