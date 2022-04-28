package com.morka.neverrelaxapp.api

import com.morka.neverrelaxapp.api.model.AdviceResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MoodApi {

    @GET("advice/search/{query}")
    suspend fun getAdvicesByQuery(
        @Path(
            value = "query",
            encoded = true
        ) query: String
    ): AdviceResponse
}