package com.example.myapplication.pages.activities.store

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private lateinit var btn_back: AppCompatImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_back = binding.appBarLayout.findViewById(R.id.back_btn) // use the correct hierarchy to access the view
        btn_back.setOnClickListener {
            val intent = Intent(this, IntroductionStore::class.java)
            startActivity(intent)
        }


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        val zoomLevel = 15f
//        val cameraUpdate = CameraUpdateFactory.zoomTo(zoomLevel)
//        mMap.uiSettings.isZoomControlsEnabled = true
//        val store = LatLng(10.7627801, 106.6827451)
//        mMap.addMarker(MarkerOptions().position(store).title("Infinity coffee"))
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(store))
        mMap = googleMap
        val zoomLevel = 15f
        mMap.uiSettings.isZoomControlsEnabled = true
        val store = LatLng(10.7627801, 106.6827451)
        mMap.addMarker(MarkerOptions().position(store).title("Infinity coffee"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store, zoomLevel))


    }
}