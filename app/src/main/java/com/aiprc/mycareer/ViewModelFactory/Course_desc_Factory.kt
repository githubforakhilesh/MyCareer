package com.aiprc.mycareer.ViewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aiprc.mycareer.ViewmodelClass.Course_Decription

class Course_desc_Factory(val context: Context):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Course_Decription(context) as T
    }
}