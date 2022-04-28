package com.morka.neverrelaxapp.api.model

data class AdviceResponse(
    val slips: List<Advice>
)

data class Advice(
    val advice: String
)
