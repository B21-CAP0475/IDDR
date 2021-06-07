package com.project.bangkit21.cap0475.iddr.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.project.bangkit21.cap0475.iddr.databinding.ActivityFusedLocationBinding
import java.util.*

class FusedLocationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFusedLocationBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFusedLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                Toast.makeText(this@FusedLocationActivity, "${location.latitude} and ${location.longitude}", Toast.LENGTH_SHORT).show()
            }
        }

        val locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@FusedLocationActivity)
        binding.btnCheckLocation.setOnClickListener {
            try {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
                }

                locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0L, 0f, locationListener)
            } catch (e : Exception) {
                Toast.makeText(this, "${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            when (grantResults[0]) {
                PackageManager.PERMISSION_DENIED -> Toast.makeText(this, "Harap izinkan akses lokasi", Toast.LENGTH_SHORT)
                    .show()//Tell to user the need of grant permission
            }
        }
    }
}