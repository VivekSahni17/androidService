package com.example.test1

import android.content.Context

class SharePreference(context: Context) {


    private var sharePreference =  context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)


    companion object {
        const val Email = "email"
    }


    fun signOut(){
        val editor = sharePreference.edit()
        editor.clear()
        editor.apply()
    }

    fun saveEmpData(key: String) {
        val editor = sharePreference.edit()
        editor.putString(Email, key)
        editor.apply()
    }


    fun fetchEmp(): String? {
        return sharePreference.getString(Email, null)
    }




}