package ui.addVehicle

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.vehicle.databinding.ItemBrandBinding

class ModelAdapter(
    private val models: List<Model>,
    private val onClick: (Model) -> Unit
) : RecyclerView.Adapter<ModelAdapter.ModelViewHolder>() {

    private var selectedPosition = -1

    inner class ModelViewHolder(
        private val binding: ItemBrandBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(model: Model, position: Int) {
            binding.tvBrand.text = model.name
            binding.ivBrandLogo.visibility = ViewGroup.GONE
            binding.radioBrand.isChecked = position == selectedPosition

            binding.root.setOnClickListener {
                select(position, model)
            }

            binding.radioBrand.setOnClickListener {
                select(position, model)
            }
        }

        private fun select(position: Int, model: Model) {
            val old = selectedPosition
            selectedPosition = position
            notifyItemChanged(old)
            notifyItemChanged(selectedPosition)
            onClick(model)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val binding =
            ItemBrandBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModelViewHolder(binding)
    }

    override fun getItemCount() = models.size

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bind(models[position], position)
    }
}
