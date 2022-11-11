package com.kelechi.countries.retrofit

import com.kelechi.countries.model.ModelData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("all")
    fun getData(): Call<List<ModelData>>

    @GET("name")
    fun  getDetails(@Query("name") name: String): Call<ModelData>



}