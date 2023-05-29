package com.aiprc.mycareer.Singleton_Class

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BaseUrl  {
    val base_url = "https://webdigimart.com/modules/restapi/"

    fun get_Instance(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


}