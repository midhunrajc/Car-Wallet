package com.app.vehicle

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.GridLayoutManager
import com.app.vehicle.databinding.ActivityMainBinding
import domain.model.Vehicle
import domain.usecase.AddVehicleUseCase
import ui.addVehicle.AddVehicleFragment
import ui.addVehicle.AddVehicleViewModel
import ui.home.VehicleAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val vehicleAdapter = VehicleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvVehicles.apply {
            adapter = vehicleAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val vehicles = listOf(
            Vehicle(1, "Honda", "City", "Petrol", "MH01AB1234", 2009, "John"),
            Vehicle(2, "Toyota", "Corolla", "Diesel", "MH02CD5678", 2010, "Nick" ) ,
            Vehicle(3, "Maruthi", "Brezza", "Diesel", "KA02CD5678", 2010, "Patove" )

        )
        vehicleAdapter.submitList(vehicles)

        // Optional: handle item clicks
        vehicleAdapter.setOnItemClickListener { vehicle ->
            Toast.makeText(this, "Clicked: ${vehicle.brand}", Toast.LENGTH_SHORT).show()
        }

        binding.btnAddVehicle.setOnClickListener {
            // Hide main UI
            binding.rvVehicles.visibility = View.GONE
            binding.topBlue.visibility = View.GONE
            binding.headerRow.visibility = View.GONE
            binding.btnAddVehicle.visibility = View.GONE

            // Show fragment container
            binding.fragmentContainer.visibility = View.VISIBLE
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, AddVehicleFragment())
                .addToBackStack(null) // allows back navigation
                .commit()
        }
    }

    override fun onResume() {
        super.onResume()
        println("midhun main activity onResume")
    }
}