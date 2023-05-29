package com.aiprc.mycareer.ViewModelFactory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aiprc.mycareer.ViewmodelClass.CourseDescViewModel

class CourseDecViewModelfactory(val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  CourseDescViewModel(context) as T
    }
}