package com.aiprc.mycareer.Repositery

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.aiprc.mycareer.Data_Classes.LoginDataClass
import com.aiprc.mycareer.ProgressBarClass
import com.aiprc.mycareer.Singleton_Class.ApiKeys
import com.aiprc.mycareer.Singleton_Class.Api_Interface
import com.aiprc.mycareer.Singleton_Class.BaseUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepositery(val context: Context) {
    var mutableLiveData = MutableLiveData<String>()
    private  var progressBar: ProgressBarClass = ProgressBarClass()
    fun api_call(email: String, password:String):MutableLiveData<String>{
        //progressBar.showProgressBar(context)
        val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
       val call: Call<LoginDataClass> = interface_instance.login(ApiKeys.login_apiKey,email,password)
        call.enqueue(object : Callback<LoginDataClass>{
            override fun onResponse(call: Call<LoginDataClass>, response: Response<LoginDataClass>) {
              //  progressBar.stopProgressBar()
                if(response.isSuccessful){
                    val dataClass = response.body()
                    if(dataClass!= null){
                        mutableLiveData.value = dataClass.status
                        Toast.makeText(context,""+dataClass.message,Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(context,"something goes wrong",Toast.LENGTH_SHORT).show()

                    }
                }else{
                    Toast.makeText(context,""+response.message(),Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
               // progressBar.stopProgressBar()
                Toast.makeText(context,""+t,Toast.LENGTH_SHORT).show()
            }

        })
             return mutableLiveData
    }

}