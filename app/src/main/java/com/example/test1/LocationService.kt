package com.example.test1

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.telecom.Call
import android.util.Log
import android.webkit.GeolocationPermissions.getInstance
import androidx.core.app.NotificationCompat
import com.google.android.gms.common.api.Response
import com.google.android.gms.common.api.internal.BackgroundDetector.getInstance
import java.util.*
import java.util.Calendar.getInstance
import javax.security.auth.callback.Callback

class LocationService:Service() {
    val Location_Channel_ID = "notification location id"


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

   @Override
    override fun onCreate() {
        super.onCreate()
       val builder:NotificationCompat.Builder = NotificationCompat.Builder(this,Location_Channel_ID)
           .setOngoing(false)
           .setSmallIcon(R.drawable.ic_baseline_notifications_24)
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           val notificationManager: NotificationManager =
               getSystemService(NOTIFICATION_SERVICE) as NotificationManager
           val notificationChannel = NotificationChannel(
               Location_Channel_ID,
               Location_Channel_ID, NotificationManager.IMPORTANCE_LOW
           )
           notificationChannel.description = Location_Channel_ID
           notificationChannel.setSound(null, null)
           notificationManager.createNotificationChannel(notificationChannel)
           startForeground(1, builder.build())
       }
   }

//    override fun onStart(intent: Intent?, startId: Int) {
//        val timer = Timer()
//        LocationHelper().startListeningUser(
//            this, object : MyLocationListner {
//                override fun onLocationChanged(location: Location?) {
//
//                    }
//            }

}
