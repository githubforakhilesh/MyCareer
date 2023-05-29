package com.aiprc.mycareer.Repositery

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.aiprc.mycareer.Data_Classes.CategoryClass
import com.aiprc.mycareer.ProgressBarClass
import com.aiprc.mycareer.Singleton_Class.ApiKeys

import com.aiprc.mycareer.Singleton_Class.Api_Interface
import com.aiprc.mycareer.Singleton_Class.BaseUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Category_Repo(val context: Context) {

    private  var progressBar: ProgressBarClass = ProgressBarClass()
     var mutableLiveData: MutableLiveData<ArrayList<CategoryClass>> = MutableLiveData()
 var arrayList: ArrayList<CategoryClass> = ArrayList()
    fun api_call(): MutableLiveData<ArrayList<CategoryClass>>{
        progressBar.showProgressBar(context)
      val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
        val call : Call<ArrayList<CategoryClass>> = interface_instance.get_category(ApiKeys.category_apiKey)
         call.enqueue(object :  Callback<ArrayList<CategoryClass>> {
             @SuppressLint("SuspiciousIndentation")
             override fun onResponse(call: Call<ArrayList<CategoryClass>>, response: Response<ArrayList<CategoryClass>>) {
                 progressBar.stopProgressBar()
                 if(response.isSuccessful){
                     val data_list = response.body()
                     if (data_list != null) {
                         for(i in 0 until data_list.size){
                           val categoryClass =  CategoryClass(data_list.get(i).id,
                                            data_list.get(i)?.category_name,
                                             data_list.get(i)?.category_url)
                             arrayList.add(categoryClass)
                         }
                         mutableLiveData.value = arrayList
                     }else{
                         Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show()

                     }

                 }else{
                     Toast.makeText(context,""+response.message(),Toast.LENGTH_SHORT).show()
                 }
             }

             override fun onFailure(call: Call<ArrayList<CategoryClass>>, t: Throwable) {
                 progressBar.stopProgressBar()
                 Toast.makeText(context,""+t,Toast.LENGTH_SHORT).show()
                 Log.d("exception==", t.toString())
             }

         })
        return mutableLiveData
    }

}