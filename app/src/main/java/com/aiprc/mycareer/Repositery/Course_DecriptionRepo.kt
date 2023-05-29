package com.aiprc.mycareer.Repositery

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.aiprc.mycareer.Data_Classes.ExploreCourse
import com.aiprc.mycareer.Data_Classes.ExploreCourseItem
import com.aiprc.mycareer.ProgressBarClass
import com.aiprc.mycareer.Singleton_Class.ApiKeys
import com.aiprc.mycareer.Singleton_Class.Api_Interface
import com.aiprc.mycareer.Singleton_Class.BaseUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Course_DecriptionRepo(val context: Context) {

    private  var progressBar: ProgressBarClass = ProgressBarClass()
    var mutableLiveData: MutableLiveData<ExploreCourse> = MutableLiveData()
    var arrayList: ExploreCourse = ExploreCourse()
    fun api_call(id: String):MutableLiveData<ExploreCourse> {

        progressBar.showProgressBar(context)
        val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
        val call: Call<ExploreCourse> = interface_instance.course_details(ApiKeys.course_details,id)
        call.enqueue(object : Callback<ExploreCourse>{
            override fun onResponse(call: Call<ExploreCourse>, response: Response<ExploreCourse>) {
                progressBar.stopProgressBar()
                if (response.isSuccessful) {
                    val data_list = response.body()
                    if (data_list != null) {
                        for (i in 0 until data_list.size) {
                            var obj = ExploreCourseItem(
                                id = data_list.get(i).id,
                                name = data_list.get(i).name,
                                url = data_list.get(i).url,
                                course_image = data_list.get(i).course_image,
                                category = data_list.get(i).category,
                                course_short_name = data_list.get(i).course_short_name,
                                duration = data_list.get(i).duration,
                                eligibility = data_list.get(i).eligibility,
                                admission_process = data_list.get(i).admission_process,
                                top_recruit = data_list.get(i).top_recruit,
                                short = data_list.get(i).short,
                                description = data_list.get(i).description,
                                price = data_list.get(i).price,
                                sem_fee = data_list.get(i).sem_fee,
                                order_by_view = data_list.get(i).order_by_view,
                                status = data_list.get(i).status
                            )
                            arrayList.add(obj)
                        }
                        mutableLiveData.value = arrayList
                    } else {
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(context, "response failed" + response.message(), Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<ExploreCourse>, t: Throwable) {
                Toast.makeText(context,""+t,Toast.LENGTH_SHORT).show()

            }

        })
        return mutableLiveData
    }
}