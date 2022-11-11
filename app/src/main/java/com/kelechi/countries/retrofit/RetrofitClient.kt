package com.kelechi.countries.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {

    companion object{
        private var retrofit : Retrofit ? =null
        private  val baseUrl = "https://restcountries.com/v3.1/"

        fun getInstance() : Retrofit? {
            if (retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }




}