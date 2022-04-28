package com.morka.neverrelaxapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morka.neverrelaxapp.R
import com.morka.neverrelaxapp.api.RetrofitInstance
import com.morka.neverrelaxapp.api.model.Advice
import com.morka.neverrelaxapp.api.model.HoroscopeData
import com.morka.neverrelaxapp.ui.home.model.Mood
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val moodsState = MutableStateFlow(
        listOf(
            MoodInfo("Calm", R.drawable.ic_calm, false, Mood.CALM),
            MoodInfo("Relaxed", R.drawable.ic_relax, false, Mood.RELAX),
            MoodInfo("Focused", R.drawable.ic_focus, false, Mood.FOCUS),
            MoodInfo("Excited", R.drawable.ic_excited, false, Mood.EXCITED),
            MoodInfo("Funny", R.drawable.ic_fun, false, Mood.FUN),
            MoodInfo("Sad", R.drawable.ic_sadness, false, Mood.SADNESS)
        )
    )

    val horoscopeRecommendationState: MutableStateFlow<HoroscopeRecommendationState> =
        MutableStateFlow(HoroscopeRecommendationState.Empty)
    val moodRecommendationsState: MutableStateFlow<MoodRecommendationsState> =
        MutableStateFlow(MoodRecommendationsState.Empty)

    fun fetchHoroscope(sunCreature: String = "aquarius") = viewModelScope.launch(Dispatchers.IO) {
        try {
            horoscopeRecommendationState.emit(HoroscopeRecommendationState.Loading)
            val horoscope = RetrofitInstance.horoscopeApi.getHoroscope(sunCreature)
            horoscopeRecommendationState.emit(HoroscopeRecommendationState.Loaded(horoscope))
        } catch (e: Exception) {
            horoscopeRecommendationState.emit(
                HoroscopeRecommendationState.Error(
                    e.localizedMessage ?: "Unknown Error."
                )
            )
        }
    }

    fun fetchMood(moodQuery: String = "happy") = viewModelScope.launch(Dispatchers.IO) {
        try {
            moodRecommendationsState.emit(MoodRecommendationsState.Loading)
            val response = RetrofitInstance.moodApi.getAdvicesByQuery(moodQuery)
            moodRecommendationsState.emit(MoodRecommendationsState.Loaded(response.slips))
        } catch (e: Exception) {
            moodRecommendationsState.emit(
                MoodRecommendationsState.Error(
                    e.localizedMessage ?: "Unknown Error"
                )
            )
        }
    }

    fun activateMood(mood: MoodInfo) = viewModelScope.launch {
        val newMoods = moodsState.value.map {
            if (it.title !== mood.title) it.copy(isSelected = false) else it.copy(isSelected = true)
        }
        moodsState.emit(newMoods)
    }
}

sealed class HoroscopeRecommendationState {
    object Empty : HoroscopeRecommendationState()
    object Loading : HoroscopeRecommendationState()
    class Loaded(val data: HoroscopeData) : HoroscopeRecommendationState()
    class Error(val message: String) : HoroscopeRecommendationState()
}

sealed class MoodRecommendationsState {
    object Empty : MoodRecommendationsState()
    object Loading : MoodRecommendationsState()
    class Loaded(val data: List<Advice>) : MoodRecommendationsState()
    class Error(val message: String) : MoodRecommendationsState()
}