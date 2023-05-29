package com.aiprc.mycareer.ViewmodelClass

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiprc.mycareer.Data_Classes.CategoryClass
import com.aiprc.mycareer.Repositery.Category_Repo

class CategoryViewModel(val context: Context) : ViewModel() {

    fun get_categoryData(): MutableLiveData<ArrayList<CategoryClass>>{
        return Category_Repo(context).api_call()
    }
}