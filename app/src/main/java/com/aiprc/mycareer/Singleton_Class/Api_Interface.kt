package com.aiprc.mycareer.Singleton_Class

import com.aiprc.mycareer.Data_Classes.CategoryClass
import com.aiprc.mycareer.Data_Classes.CourseDescDataClass
import com.aiprc.mycareer.Data_Classes.ExploreCourse
import com.aiprc.mycareer.Data_Classes.LoginDataClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api_Interface {
    @GET("category")
    fun get_category(@Query("X-API-KEY") api_key: String) : Call<ArrayList<CategoryClass>>

    @GET("course")
   fun get_course(@Query("X-API-KEY") api_key: String) : Call<ArrayList<CourseDescDataClass>>

    @GET("course")
    fun get_explore_course(@Query("X-API-KEY") api_key: String) : Call<ExploreCourse>

    @GET("login")
    fun login(@Query("X-API-KEY") api_key: String,
               @Query("email") email:String,
               @Query("password") password: String) : Call<LoginDataClass>

    @GET("student/registration")
    fun student_registration(@Query("X-API-KEY") api_key: String,
                             @Query("pass_twelve") pass_twelve:String,
                             @Query("first_name") first_name: String,
                             @Query("last_name") last_name: String,
                             @Query("email") email: String,
                             @Query("course") course: String,
                             @Query("certificate") certificate: String,
                             @Query("batch") batch: String,
                             @Query("password") password: String,
                             @Query("mobile") mobile: String,
                             @Query("address") address: String) : Call<LoginDataClass>
    @GET("course_detial")
    fun course_details(@Query("X-API-KEY") api_key: String,
              @Query("course") course:String) : Call<ExploreCourse>

}