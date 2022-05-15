package com.example.test1.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test1.fragment.LoginFragment
import com.example.test1.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity_main)
        val loginFragment =  LoginFragment()
        supportFragmentManager.beginTransaction().add(R.id.Fragment_Container,loginFragment).commit()

    }
}