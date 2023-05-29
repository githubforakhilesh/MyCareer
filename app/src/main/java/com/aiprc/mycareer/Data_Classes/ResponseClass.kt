package com.aiprc.mycareer.Data_Classes

 class ResponseClass<T> {
     lateinit var arrayList: ArrayList<T>
     fun get_responseList(): ArrayList<T>{
        return arrayList
     }
 }

