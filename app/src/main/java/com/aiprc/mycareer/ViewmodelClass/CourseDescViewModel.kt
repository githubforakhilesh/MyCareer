package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Data_Classes.CourseDescDataClass
import com.aiprc.mycareer.Repositery.CourseDescRepo

class CourseDescViewModel(val context: Context): ViewModel() {

    fun get_courseData(): MutableLiveData<ArrayList<CourseDescDataClass>> {
        return CourseDescRepo(context).api_call()
    }
}