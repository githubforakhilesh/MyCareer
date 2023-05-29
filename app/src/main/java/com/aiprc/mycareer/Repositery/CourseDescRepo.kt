package com.aiprc.mycareer.Repositery

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.aiprc.mycareer.Data_Classes.CourseDescDataClass
import com.aiprc.mycareer.ProgressBarClass
import com.aiprc.mycareer.Singleton_Class.ApiKeys
import com.aiprc.mycareer.Singleton_Class.Api_Interface
import com.aiprc.mycareer.Singleton_Class.BaseUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseDescRepo(val context: Context) {

    private  var progressBar: ProgressBarClass = ProgressBarClass()
    var mutableLiveData: MutableLiveData<ArrayList<CourseDescDataClass>> = MutableLiveData()
    var arrayList: ArrayList<CourseDescDataClass> = ArrayList()
    fun api_call(): MutableLiveData<ArrayList<CourseDescDataClass>> {
      //  progressBar.showProgressBar(context)
        val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
        val call : Call<ArrayList<CourseDescDataClass>> = interface_instance.get_course(ApiKeys.courses_apikey)
        call.enqueue(object : Callback<ArrayList<CourseDescDataClass>> {
            @SuppressLint("SuspiciousIndentation")
            override fun onResponse(
                call: Call<ArrayList<CourseDescDataClass>>,
                response: Response<ArrayList<CourseDescDataClass>>
            ) {
              //  progressBar.stopProgressBar()
                if (response.isSuccessful) {
                    val data_list = response.body()
                    if (data_list != null) {
                        for (i in 0 until data_list.size) {
                            val courseDescDataClass = CourseDescDataClass(
                                id = data_list.get(i)?.id,
                                category = data_list.get(i)?.category,
                                url = data_list.get(i)?.url,
                                name = data_list.get(i)?.name,
                                description = data_list.get(i)?.description,
                                price = data_list.get(i)?.price,
                                discount = data_list.get(i)?.discount,
                                short = data_list.get(i)?.short,
                                order_by_view = data_list.get(i)?.order_by_view,
                                status = data_list.get(i)?.status,
                                course_image = data_list.get(i)?.course_image
                            )
                            arrayList.add(courseDescDataClass)
                        }
                        mutableLiveData.value = arrayList
                    } else {
                        Toast.makeText(context, "No Data Found", Toast.LENGTH_SHORT).show()

                    }

                } else {
                    Toast.makeText(context, "" + response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<CourseDescDataClass>>, t: Throwable) {
              //  progressBar.stopProgressBar()
                Toast.makeText(context, "" + t, Toast.LENGTH_SHORT).show()
                Log.d("exception==", t.toString())
            }

        })
        return mutableLiveData
    }
}