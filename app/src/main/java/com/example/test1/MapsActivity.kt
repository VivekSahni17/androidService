package com.example.test1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*

internal  class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnPolylineClickListener {
    private val PATTERN_GAP_LENGTH_PX = 20
    private val DOT: PatternItem = Dot()
    private val GAP: PatternItem = Gap(PATTERN_GAP_LENGTH_PX.toFloat())
    private val COLOR_BLACK_ARGB = -0x1000000
    private val POLYLINE_STROKE_WIDTH_PX = 12

    // Create a stroke pattern of a gap followed by a dot.
    private val PATTERN_POLYLINE_DOTTED = listOf(GAP, DOT)

    //lateinit var mMap:GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {

        val polyline = googleMap.addPolyline(PolylineOptions()
            .clickable(true)
            .add(
                LatLng(30.6942, 76.8606),
                LatLng(30.6425, 76.8173),
                LatLng(30.7202, 76.8375)))
        polyline.tag = "A"
        stylePolyline(polyline)
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(30.7333, 76.7794), 4f))
//        googleMap.setOnPolylineClickListener(this)

        val polyline1 = googleMap.addPolyline(PolylineOptions()
            .clickable(true)
            .add(
                LatLng(30.6942, 76.8606),
                LatLng(30.7255, 76.8463),
                LatLng(31.1471, 75.3412)))
        polyline.tag = "B"
        stylePolyline(polyline1)

    }



    private fun stylePolyline(polyline: Polyline) {

        val type = polyline.tag?.toString() ?: ""
        when (type) {
            "A" -> {
                // Use a custom bitmap as the cap at the start of the line.
                polyline.startCap = CustomCap(
                    BitmapDescriptorFactory.fromResource(R.drawable.ic_arrow), 10f)
            }
            "B" -> {
                polyline.startCap = RoundCap()
            }
        }

            polyline.endCap = RoundCap()
                    polyline.endCap = RoundCap()
                    polyline.width = POLYLINE_STROKE_WIDTH_PX.toFloat()
                    polyline.color = COLOR_BLACK_ARGB
                    polyline.jointType = JointType.ROUND


    }

    override fun onPolylineClick(poline: Polyline) {
        if (poline.pattern == null || !poline.pattern!!.contains(DOT)){
            poline.pattern = PATTERN_POLYLINE_DOTTED
        }else
            poline.pattern = null

        }

    }
