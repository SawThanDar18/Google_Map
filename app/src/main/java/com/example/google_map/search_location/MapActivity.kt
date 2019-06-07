package com.example.google_map.search_location

import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.Manifest
import android.support.v4.app.FragmentActivity
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.google_map.R
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapActivity : FragmentActivity(), OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
 GoogleApiClient.OnConnectionFailedListener{

    private var mMap : GoogleMap? = null
    internal lateinit var mLastLocation : Location
    internal var mCurrLocationMarker : Marker? = null
    internal var mGoogleApiClient : GoogleApiClient? = null
    internal lateinit var mLocationRequest: LocationRequest

    private lateinit var search : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_activity)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        //if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                buildGoogleApiClient()
                mMap!!.isMyLocationEnabled = true
            //}
        }/*else{
            buildGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
        }*/
    }

    fun searchLocation(view: View) {

        if(view.id == R.id.button_search){

            val locationSearch:EditText = findViewById<EditText>(R.id.editText)
            lateinit var location: String
            location = locationSearch.text.toString()
            var addressList: List<Address>? = null
            val options = MarkerOptions()

            if (location == null || location == "") {
                Toast.makeText(applicationContext,"provide location",Toast.LENGTH_SHORT).show()
            }
            else{
                val geoCoder = Geocoder(this)
                try {
                    addressList = geoCoder.getFromLocationName(location, 1)

                } catch (e: IOException) {
                    e.printStackTrace()
                }

                for(i in addressList!!.indices){
                    val address = addressList!![i]
                    val latLng = LatLng(address.latitude, address.longitude)
                    options.position(latLng)
                    options.title(location)
                    mMap!!.addMarker(options)
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

                }
                /*val address = addressList!![0]
                val latLng = LatLng(address.latitude, address.longitude)
                mMap!!.addMarker(MarkerOptions().position(latLng).title(location))
                mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))
                Toast.makeText(applicationContext, address.latitude.toString() + " " + address.longitude, Toast.LENGTH_LONG).show()
           */ }
        }
    }

    protected fun buildGoogleApiClient(){
        mGoogleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API).build()

        mGoogleApiClient!!.connect()
    }

    override fun onConnected(bundle: Bundle?) {

        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 1000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onConnectionSuspended(i: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLocationChanged(location: Location){

        mLastLocation = location

        if(mCurrLocationMarker != null){
            mCurrLocationMarker!!.remove()
        }

        //place current location marker
        val latLng = LatLng(location.latitude, location.longitude)
        val markerOptions = MarkerOptions()
        markerOptions.position(latLng)
        markerOptions.title("Current Position")
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        mCurrLocationMarker = mMap!!.addMarker(markerOptions)

        //move map camera
        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        mMap!!.animateCamera(CameraUpdateFactory.zoomTo(11f))

        //stop location updates
        if(mGoogleApiClient != null){
            LocationServices.getFusedLocationProviderClient(this)
        }
    }

    override fun onConnectionFailed(connectionResult : ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
