package com.morka.neverrelaxapp.api

import com.morka.neverrelaxapp.api.model.HoroscopeData
import retrofit2.http.*

interface HoroscopeApi {

    @POST(".")
    suspend fun getHoroscope(
        @Query("sign") sign: String,
        @Query("day") day: String = "today",
        @Header("x-rapidapi-host") host: String = "sameer-kumar-aztro-v1.p.rapidapi.com",
        @Header("x-rapidapi-key") key: String = "f382cecaf5msh20615aae7f2daedp15743ajsnded8ea2e90bb",
    ): HoroscopeData
}