package com.example.viewpagerproblem.ui.data

data class CyclicViewPagerState(
    val curentLocale: CurrentLocale,
    val pages: List<IViewData>,
    val cyclicityState: CyclicityState
) {

    sealed class CyclicityState {
        object Enabled : CyclicityState()

        object Disabled : CyclicityState()
    }
}

enum class CurrentLocale {
    ENGLISH, FRENCH
}