package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Data_Classes.ExploreCourse
import com.aiprc.mycareer.Repositery.ExploreCourseRepo

class ExploreCourseViewmodel(val context: Context) : ViewModel() {

    fun get_Courses():MutableLiveData<ExploreCourse>{

     return ExploreCourseRepo(context).api_call()
    }

}