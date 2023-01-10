package com.example.viewpagerproblem.ui.data

interface ICyclicViewPager {
    fun initialize(
        onClickedCallback: (id: String) -> Unit,
        onCloseClickedCallback: (id: String) -> Unit
    )

    fun setState(viewPagerState: CyclicViewPagerState)
}