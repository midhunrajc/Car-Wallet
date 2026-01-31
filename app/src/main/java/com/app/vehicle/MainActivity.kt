package com.app.vehicle

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.vehicle.R
import com.app.vehicle.databinding.ActivityMainBinding
import data.local.AppDatabase
import data.local.entity.toDomain
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ui.addVehicle.AddVehicleFragment
import ui.addVehicle.AddVehicleViewModel
import ui.addVehicle.AddVehicleViewModelFactory
import ui.home.VehicleAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val vehicleAdapter = VehicleAdapter()
    private lateinit var viewModel: AddVehicleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup RecyclerView
        binding.rvVehicles.apply {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        // Setup ViewModel
        val dao = AppDatabase.getInstance(this).vehicleDao()
        val factory = AddVehicleViewModelFactory(dao)
        viewModel = ViewModelProvider(this, factory)[AddVehicleViewModel::class.java]

        // Observe vehicles from database
        lifecycleScope.launch {
            viewModel.allVehicles.collectLatest { entities ->
                val vehicles = entities.map { it.toDomain() }
                println("mdn data coming")// convert to domain model
                vehicleAdapter.submitList(vehicles)
            }
        }


        // Add Vehicle button
        binding.btnAddVehicle.setOnClickListener {
            binding.rvVehicles.visibility = View.GONE
            binding.topBlue.visibility = View.GONE
            binding.headerRow.visibility = View.GONE
            binding.btnAddVehicle.visibility = View.GONE
            binding.fragmentContainer.visibility = View.VISIBLE

            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddVehicleFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
