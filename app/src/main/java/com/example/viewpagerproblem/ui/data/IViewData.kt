package com.example.viewpagerproblem.ui.data

interface IViewData

sealed class ViewData : IViewData {
    data class Type0(
        val id: String,
        val title: String,
        val text: String,
    ) : ViewData()

    data class Type1(
        val id: String,
        val title: String,
        val text: String,
    ) : ViewData()
}
