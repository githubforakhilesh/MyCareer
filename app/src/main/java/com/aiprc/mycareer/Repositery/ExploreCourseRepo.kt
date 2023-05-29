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

class ExploreCourseRepo (val context: Context){
    private  var progressBar: ProgressBarClass = ProgressBarClass()
    var mutableLiveData: MutableLiveData<ExploreCourse> = MutableLiveData()
     var arrayList: ExploreCourse = ExploreCourse()
   fun api_call():MutableLiveData<ExploreCourse>{

       progressBar.showProgressBar(context)
       val interface_instance = BaseUrl.get_Instance().create(Api_Interface::class.java)
       val call : Call<ExploreCourse> = interface_instance.get_explore_course(ApiKeys.courses_apikey)
       call.enqueue(object : Callback<ExploreCourse>{
           override fun onResponse(call: Call<ExploreCourse>, response: Response<ExploreCourse>) {
               progressBar.stopProgressBar()
              if(response.isSuccessful){
                 val exploreCourse = response.body()
                 if(exploreCourse!= null){
                     for(i in 0 until exploreCourse.size){
                         var course_items = ExploreCourseItem(
                             id = exploreCourse.get(i)?.id,
                             name = exploreCourse.get(i)?.name,
                             url= exploreCourse.get(i)?.url,
                             course_image= exploreCourse.get(i)?.course_image,
                             category = exploreCourse.get(i)?.category,
                             course_short_name = exploreCourse.get(i)?.course_short_name,
                             duration = exploreCourse.get(i)?.duration,
                             eligibility = exploreCourse.get(i)?.eligibility,
                             admission_process = exploreCourse.get(i)?.admission_process,
                             top_recruit = exploreCourse.get(i)?.top_recruit,
                             short = exploreCourse.get(i)?.short,
                             description = exploreCourse.get(i)?.description,
                             price = exploreCourse.get(i)?.price,
                             sem_fee = exploreCourse.get(i)?.sem_fee,
                             order_by_view = exploreCourse.get(i)?.order_by_view,
                             status = exploreCourse.get(i)?.status
                         )
                         arrayList.add(course_items)
                     }
                     mutableLiveData.value = arrayList
                 }else{
                     Toast.makeText(context,"No Data Found",Toast.LENGTH_SHORT).show()
                 }
              }else{
                  Toast.makeText(context,""+response.message(),Toast.LENGTH_SHORT).show()
              }
           }

           override fun onFailure(call: Call<ExploreCourse>, t: Throwable) {
              progressBar.stopProgressBar()
               Toast.makeText(context,""+t,Toast.LENGTH_SHORT).show()

           }

       } )
        return mutableLiveData
   }
}