package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Repositery.LoginRepositery

class LoginViewModel(val context: Context): ViewModel() {

    fun Login(email:String,password:String):MutableLiveData<String>{

       return LoginRepositery(context).api_call(email,password)

    }
}