package com.example.viewpagerproblem.ui.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView
import com.example.viewpagerproblem.R
import com.example.viewpagerproblem.ui.data.ViewData

class Type1View @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val titleTv: TextView
    private val textTv: TextView

    private var onClickedCallback: ((id: String) -> Unit)? = null
    private var onCloseClickedCallback: ((id: String) -> Unit)? = null

    init {
        inflate(context, R.layout.view_type_1, this)

        titleTv = findViewById(R.id.title)
        textTv = findViewById(R.id.text)
    }

    fun acceptData(data: ViewData.Type1) {
        titleTv.text = data.title
        textTv.text = data.text

        // click listeners and more code
    }

    fun setOnClickedCallback(onClickedCallback: (id: String) -> Unit) {
        this.onClickedCallback = onClickedCallback
    }

    fun setOnCloseClickedCallback(onCloseClickedCallback: (id: String) -> Unit) {
        this.onCloseClickedCallback = onCloseClickedCallback
    }
}