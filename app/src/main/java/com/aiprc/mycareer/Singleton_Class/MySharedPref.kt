package com.aiprc.mycareer.Singleton_Class

import android.content.Context
import android.preference.PreferenceManager

class MySharedPref(val context: Context) {
    private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
    companion object{
        val login:String = "login"
        val id:String = "Id"
    }
    fun setPreferences(keyString:String,value:String){
      val editor = sharedPreferences.edit()
        editor.putString(keyString,value)
        editor.apply()
    }
     fun getPreferences(keyString: String): String? {
         var sh = sharedPreferences.getString(keyString,"")
         return  sh
     }
}