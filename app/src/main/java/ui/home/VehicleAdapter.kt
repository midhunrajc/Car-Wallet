package ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.ItemVehicleBinding
import domain.model.Vehicle

class VehicleAdapter :
    ListAdapter<Vehicle, VehicleAdapter.VehicleViewHolder>(DiffCallback) {

    // Lambda to handle item clicks
    private var onItemClick: ((Vehicle) -> Unit)? = null

    // Public function to set the click listener
    fun setOnItemClickListener(listener: (Vehicle) -> Unit) {
        onItemClick = listener
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Vehicle>() {
            override fun areItemsTheSame(old: Vehicle, new: Vehicle) =
                old.id == new.id

            override fun areContentsTheSame(old: Vehicle, new: Vehicle) =
                old == new
        }
    }

    inner class VehicleViewHolder(
        private val binding: ItemVehicleBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            // Handle click on the whole item
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(getItem(position))
                }
            }
        }

        fun bind(vehicle: Vehicle) {
            binding.tvBrand.text = vehicle.brand
            binding.tvModel.text = vehicle.model
            binding.tvFuel.text = vehicle.fuelType
            binding.tvNumber.text = vehicle.vehicleNumber
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val binding = ItemVehicleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VehicleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
