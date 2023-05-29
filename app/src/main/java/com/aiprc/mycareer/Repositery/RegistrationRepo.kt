package com.aiprc.mycareer.Repositery

import android.content.Context
import android.util.Log
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

class RegistrationRepo(val context: Context) {
    private  var progressBar: ProgressBarClass = ProgressBarClass()
    var mutableLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun api_call( first_name:String, last_name: String, email: String, qualification: String, course: String, certificate: String, batch: String, password: String,mobile:String,address:String): MutableLiveData<Boolean>{
       // progressBar.showProgressBar(context = context)
        val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
        val call : Call<LoginDataClass> = interface_instance.student_registration(api_key = ApiKeys.registration_apiKey,
        pass_twelve = qualification,first_name= first_name,last_name= last_name,email= email,course= course,certificate= certificate,batch=batch,password= password, mobile = mobile, address = address)
        call.enqueue(object : Callback<LoginDataClass> {
            override fun onResponse(call: Call<LoginDataClass>, response: Response<LoginDataClass>) {
              //  progressBar.stopProgressBar()
                if(response.isSuccessful){
                     val value = response.body()
                    if(value!= null){
                        if(value.status.toBoolean()){
                            mutableLiveData.value = value.status.toBoolean()
                        }else{
                            Toast.makeText(context,""+value.message,Toast.LENGTH_SHORT).show()
                        }

                    }else{
                        Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context,"response code: "+response.body()?.status,Toast.LENGTH_SHORT).show()
                   // Log.i("error=",response.errorBody().toString())
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