package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Repositery.RegistrationRepo

class RegistrationViewModel(val context: Context): ViewModel() {

    fun register(first_name:String,
                 last_name: String,
                 email: String,
                 qualification: String,
                 course: String,
                 certificate: String,
                 batch: String,
                 password: String,
                 mobile:String,
                 address:String): MutableLiveData<Boolean>{

     return RegistrationRepo(context).api_call(first_name, last_name, email, qualification, course, certificate, batch, password,mobile,address)
    }
}