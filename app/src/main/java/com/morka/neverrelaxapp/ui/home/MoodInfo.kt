package com.morka.neverrelaxapp.ui.home

import com.morka.neverrelaxapp.ui.home.model.Mood

data class MoodInfo(
    val title: String,
    val image: Int,
    var isSelected: Boolean,
    val mood: Mood
)
