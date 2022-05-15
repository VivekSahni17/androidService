package com.example.test1

import android.app.*
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.provider.Settings
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.example.test1.activity.EmployeeDetailsActivity


class PlayMusicService : Service() {

     lateinit var serviceMusicPlayer: MediaPlayer
    val CHANNEL_ID = "ForegroundServiceChannel"
    //lateinit var btnpause:Button
    //private var  serviceMusicPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    @Override
    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this,"Service Created",Toast.LENGTH_SHORT).show()
        serviceMusicPlayer =  MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
        serviceMusicPlayer.isLooping =  true
    }

    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        val intent =  intent?.getStringExtra("Key")
        musicNotificationChannel()
        val notificationIntent = Intent(this,EmployeeDetailsActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0)
        val notification = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText(intent)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setContentIntent(pendingIntent).build()
        startForeground(1,notification)
        serviceMusicPlayer.start()
    }


//       @Override
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
////        serviceMusicPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
////        serviceMusicPlayer.isLooping = true
//
//       // return START_STICKY
//    }

//
//    fun playMusic(){
//        if (!serviceMusicPlayer.equals(null) && !serviceMusicPlayer.isPlaying) {
//            //if (serviceMusicPlayer.equals(null))
//            //if (!serviceMusicPlayer.isPlaying) {
//                serviceMusicPlayer.start()
//            }
//        }
//    //}
//
//
//   fun pauseMusic() {
//        if (!serviceMusicPlayer.equals(null) && serviceMusicPlayer.isPlaying) {
//            serviceMusicPlayer.pause()
//
//        }
//        }


//    @Override
//    override fun onRebind(intent: Intent?) {
//        super.onRebind(intent)
//        player.pause()
//        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
//    }

//    @Override
//    fun onPause(){
//        super.onDestroy()
//        player.pause()
//    }


        @Override
        override fun onDestroy() {
            super.onDestroy()
            Toast.makeText(this," Service Pause ",Toast.LENGTH_SHORT).show()
            serviceMusicPlayer.stop()
        }

    fun musicNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)

    }





    }
}
