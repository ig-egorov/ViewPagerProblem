package com.example.viewpagerproblem.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpagerproblem.R
import com.example.viewpagerproblem.adapter.AdapterUtils
import com.example.viewpagerproblem.adapter.CyclicAdapter
import com.example.viewpagerproblem.ui.data.CyclicViewPagerState
import com.example.viewpagerproblem.ui.data.ICyclicViewPager

class CyclicViewPager @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    ICyclicViewPager {

    private val cyclicViewPager: ViewPager2

    private lateinit var onClickedCallback: (id: String) -> Unit
    private lateinit var onCloseClickedCallback: (id: String) -> Unit
    private lateinit var adapter: CyclicAdapter

    init {
        LayoutInflater
            .from(context)
            .inflate(R.layout.view_cyclic_view_pager, this, true)

        cyclicViewPager = findViewById(R.id.cyclic_view_pager)

        (cyclicViewPager.getChildAt(0) as RecyclerView).apply {
            clipToPadding = false
            clipChildren = false
            overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        cyclicViewPager.offscreenPageLimit = 3
    }

    override fun initialize(
        onClickedCallback: (id: String) -> Unit,
        onCloseClickedCallback: (id: String) -> Unit
    ) {
        this.onClickedCallback = onClickedCallback
        this.onCloseClickedCallback = onCloseClickedCallback

        adapter = CyclicAdapter(
            onClickedCallback,
            onCloseClickedCallback,
        )

        cyclicViewPager.adapter = adapter
    }

    override fun setState(viewPagerState: CyclicViewPagerState) {

        when (viewPagerState.cyclicityState) {

            is CyclicViewPagerState.CyclicityState.Enabled -> {
                adapter.submitList(viewPagerState.pages) {
                    adapter.isCyclic = true

                    cyclicViewPager.post {
                        cyclicViewPager.setCurrentItem(
                            // Setting view pager item to +- 500
                            AdapterUtils.getCyclicInitialPosition(
                                adapter.currentList.size
                            ), false
                        )
                    }
                }
            }

            is CyclicViewPagerState.CyclicityState.Disabled -> {
                if (viewPagerState.pages.size == 1 && adapter.isCyclic) {
                    cyclicViewPager.setCurrentItem(0, false)
                    adapter.isCyclic = false
                }

                adapter.submitList(viewPagerState.pages)
            }
        }
    }
}