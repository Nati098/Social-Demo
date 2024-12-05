package ru.social.demo.services.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://www.dnd5eapi.co/api/"

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

object RpgApiClient {

    val service: RpgApiService by lazy {
        RetrofitClient.retrofit.create(RpgApiService::class.java)
    }

}