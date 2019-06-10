package com.example.google_map

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions



class MapsActivity : AppCompatActivity(), OnMapReadyCallback{

    private var googleMap : GoogleMap? = null
    lateinit var latLng : LatLng

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(p0: GoogleMap?) {

        googleMap = p0

        //val yangon = googleMap!!.addMarker(MarkerOptions().position(LatLng(17.132582, 96.141762)).title("yangon"))
        /*val latLng = LatLng(17.132582, 96.141762)
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
         }*/

        //addMarkers()

        var markerList = ArrayList<MarkerData>()

        markerList.add(MarkerData(16.811520, 96.176338,"Tamwe"))
        markerList.add(MarkerData(16.836491, 96.165390, "YanKin"))
        markerList.add(MarkerData(16.913553,96.170574, "North Okkalapa"))
        markerList.add(MarkerData(16.864718,96.132060, "Air-port"))
        markerList.add(MarkerData(16.793369, 96.145599, "Dagon"))
        markerList.add(MarkerData(16.769100, 96.166890, "TheinPhyu"))
        markerList.add(MarkerData(16.812536, 96.225327, "TharKayTa"))
        markerList.add(MarkerData(16.824600, 96.135109, "KaMarYut"))

        for(index in 0..markerList.size-1){
            latLng = LatLng(markerList.get(index).latitude, markerList.get(index).longitude)
            googleMap!!.addMarker(MarkerOptions().position(latLng).title(markerList.get(index).title))
        }

        googleMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f))
    }

    /*private fun addMarkers() {
        if(googleMap != null){
            googleMap!!.addMarker(MarkerOptions().position(LatLng(17.132582, 96.141762)).title("Yangon"))
            googleMap!!.addMarker(MarkerOptions().position(LatLng(19.745, 96.12972)).title("NayPyiTaw"))

        }
    }*/
}
