package com.example.google_map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions



class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private var googleMap : GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {

        googleMap = p0

        val latLng = LatLng(17.132582, 96.141762)
        val markerOptions : MarkerOptions = MarkerOptions().position(latLng).title("Yangon")

        val latLng2 = LatLng(19.745, 96.12972)
        val markerOptions2 : MarkerOptions = MarkerOptions().position(latLng2).title("Naypyitaw")

        val latLng3 = LatLng(16.913553,96.170574)
        val markerOptions3 : MarkerOptions = MarkerOptions().position(latLng3).title("North Okkalapa")

        val latLng4 = LatLng(16.864718,96.132060)
        val markerOptions4 : MarkerOptions = MarkerOptions().position(latLng4).title("Airport")

        val zoomLevel = 12.0f

        googleMap.let {
            it!!.addMarker(markerOptions)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))

            it!!.addMarker(markerOptions2)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, zoomLevel))

            it!!.addMarker(markerOptions3)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng3, zoomLevel))

            it!!.addMarker(markerOptions4)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng4, zoomLevel))
         }

        /*googleMap.let {
            it!!.addMarker(markerOptions2)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng2, zoomLevel))
        }*/
    }
}
