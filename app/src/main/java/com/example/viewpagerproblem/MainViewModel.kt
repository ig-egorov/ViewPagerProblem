package com.example.viewpagerproblem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viewpagerproblem.ui.data.CurrentLocale
import com.example.viewpagerproblem.ui.data.CyclicViewPagerState
import com.example.viewpagerproblem.ui.data.ViewData

class MainViewModel : ViewModel() {
    private val _state: MutableLiveData<CyclicViewPagerState> = MutableLiveData()
    val state: LiveData<CyclicViewPagerState>
        get() = _state

    init {
        _state.value = CyclicViewPagerState(
            curentLocale = CurrentLocale.ENGLISH,
            pages = listOf(
                ViewData.Type0(
                    id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                    title = "Hello",
                    text = "world"
                ),
                ViewData.Type1(
                    id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                    title = "Hello",
                    text = "world"
                ),
                ViewData.Type0(
                    id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                    title = "Hello",
                    text = "world"
                ),
                ViewData.Type1(
                    id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                    title = "Hello",
                    text = "world"
                )
            ),
            cyclicityState = CyclicViewPagerState.CyclicityState.Enabled
        )
    }

    fun changeLocale() {
        when (state.value?.curentLocale) {
            CurrentLocale.ENGLISH -> _state.value = CyclicViewPagerState(
                curentLocale = CurrentLocale.FRENCH,
                pages = listOf(
                    ViewData.Type0(
                        id = "${Math.random() * 10000}_${CurrentLocale.FRENCH.name}",
                        title = "Bonjour",
                        text = "le monde"
                    ),
                    ViewData.Type1(
                        id = "${Math.random() * 10000}_${CurrentLocale.FRENCH.name}",
                        title = "Bonjour",
                        text = "le monde"
                    ),
                    ViewData.Type0(
                        id = "${Math.random() * 10000}_${CurrentLocale.FRENCH.name}",
                        title = "Bonjour",
                        text = "le monde"
                    ),
                    ViewData.Type1(
                        id = "${Math.random() * 10000}_${CurrentLocale.FRENCH.name}",
                        title = "Bonjour",
                        text = "le monde"
                    )
                ),
                cyclicityState = CyclicViewPagerState.CyclicityState.Enabled
            )
            CurrentLocale.FRENCH -> _state.value = CyclicViewPagerState(
                curentLocale = CurrentLocale.ENGLISH,
                pages = listOf(
                    ViewData.Type0(
                        id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                        title = "Hello",
                        text = "world"
                    ),
                    ViewData.Type1(
                        id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                        title = "Hello",
                        text = "world"
                    ),
                    ViewData.Type0(
                        id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                        title = "Hello",
                        text = "world"
                    ),
                    ViewData.Type1(
                        id = "${Math.random() * 10000}_${CurrentLocale.ENGLISH.name}",
                        title = "Hello",
                        text = "world"
                    )
                ),
                cyclicityState = CyclicViewPagerState.CyclicityState.Enabled
            )
            else -> return
        }
    }

}