package com.example.test1.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.test1.DatabaseHelper
import com.example.test1.PlayMusicService
import com.example.test1.R
import com.example.test1.SharePreference
import com.google.android.material.appbar.MaterialToolbar

 class EmployeeDetailsActivity : AppCompatActivity(){
    //private lateinit var employeeId: TextView
    private lateinit var email: TextView
    //lateinit var name: TextView
    //lateinit var allData: TextView
    //private lateinit var password: TextView
   // lateinit var db: DatabaseHelper
    //lateinit var start:Button
    private lateinit var stop:Button
    lateinit var mplayer:PlayMusicService
    lateinit var playButton:Button
    lateinit var pauseButton:Button
    private lateinit var topAppBar: MaterialToolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emaployee_details2)
        topAppBar = findViewById(R.id.topAppBar)
        email = findViewById(R.id.tvemail)
        //start =  findViewById(R.id.btnStart)
        stop =  findViewById(R.id.btnStop)
        pauseButton =  findViewById(R.id.btnPause)
        playButton = findViewById(R.id.btnplay)
        //db = DatabaseHelper(this)
        val mSharePreference = SharePreference(this)
        mSharePreference.fetchEmp()
        email.text =  mSharePreference.fetchEmp()
        mplayer = PlayMusicService()
        //mplayer.serviceMusicPlayer
        // start.setOnClickListener(this)
        //stop.setOnClickListener(this)
        //pause.setOnClickListener(this)
//        startService(Intent(this, PlayMusicService::class.java))
//        Toast.makeText(this,"Music Service Start",Toast.LENGTH_SHORT).show()

        pauseButton.setOnClickListener {
//            val serviceIntent = Intent(this,PlayMusicService::class.java)
//            stopService(serviceIntent)
            //mplayer.playMusic()
            stopService(Intent(this,PlayMusicService::class.java))

            //Toast.makeText(this," Service Music Pause ",Toast.LENGTH_SHORT).show()
        }


        playButton.setOnClickListener{
            //mplayer.pauseMusic()
            //startService(Intent(this,PlayMusicService::class.java))
            val serviceIntent =  Intent(this,PlayMusicService::class.java)
            serviceIntent.putExtra("Key","Foreground Music Service Start")
            ContextCompat.startForegroundService(this,serviceIntent)
            //Toast.makeText(this," Service Music Play ",Toast.LENGTH_SHORT).show()
        }

//        stop.setOnClickListener{
//            stopService(Intent(this, PlayMusicService::class.java))
//            Toast.makeText(this,"Music Service Stop",Toast.LENGTH_SHORT).show()
//        }
//
//        playButton.setOnClickListener {
//            mplayer.onPause()
//                playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
//            }
//              else {
//                  playButton.setBackgroundResource(R.drawable.ic_baseline_pause_24)
//            }
//        }
//        }

//        pauseButton.setOnClickListener(this)
//        resumeButton.setOnClickListener(this)









        //allData.text = empList.toString()
       // val emailText = email.text.toString()
        //val name = name.text.toString()
        //val password =  password.text.toString()




//

////     override fun onClick(v: View?) {
//         if (v == playButton) {
//            mplayer.onPause()
//             playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
//
//         } else {
//             playButton.setBackgroundResource(R.drawable.ic_baseline_pause_24)
//         }
//     }
//        for (items in empList) {
//            email.text = items.email
//            name.text =  items.name
//        }


        topAppBar.setNavigationOnClickListener {
            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()


        }
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when(menuItem.itemId){
            R.id.topLogout -> {

                val sf =  SharePreference(this)
                sf.signOut()
               // empList.clear()
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
                true
            }
                else-> false
            }
        }
    }

//     override fun onClick(v: View?) {
//
//         if (v == playButton) {
//             playButton.setBackgroundResource(R.drawable.ic_baseline_pause_24)
//
//             mplayer.pauseMusic()
//
//         } else {
//             playButton.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24)
//             mplayer.playMusic()
//         }
//     }



//     override fun onClick(v: View?) {
//        if (v == pauseButton ){
//            mplayer.pauseMusic()
//           // startService(Intent(this, PlayMusicService::class.java))
//         Toast.makeText(this,"Music Pause Start",Toast.LENGTH_SHORT).show()
//     }
//
//        else if(v == resumeButton ) {
//            mplayer.playMusic()
//            Toast.makeText(this,"Music Resume Stop",Toast.LENGTH_SHORT).show()
//        }
//         //stopService(Intent(this,PlayMusicService::class.java))

     //}



 }
 //}







