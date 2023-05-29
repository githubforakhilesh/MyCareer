package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Data_Classes.ExploreCourse
import com.aiprc.mycareer.Repositery.Course_DecriptionRepo

class Course_Decription (val context: Context): ViewModel() {

    fun get_Courses(id:String): MutableLiveData<ExploreCourse> {

        return Course_DecriptionRepo(context).api_call(id)
    }
}