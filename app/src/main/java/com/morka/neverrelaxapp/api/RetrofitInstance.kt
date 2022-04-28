package com.morka.neverrelaxapp.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {
    private val interceptor : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS)
        .build()

    private val moodRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.adviceslip.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val moodApi: MoodApi by lazy {
        moodRetrofit.create(MoodApi::class.java)
    }

    private val horoscopeRetrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://sameer-kumar-aztro-v1.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    val horoscopeApi: HoroscopeApi by lazy {
        horoscopeRetrofit.create(HoroscopeApi::class.java)
    }
}