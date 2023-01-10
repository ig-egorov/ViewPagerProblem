package com.example.viewpagerproblem.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.viewpagerproblem.ui.data.IViewData
import com.example.viewpagerproblem.ui.data.ViewData
import com.example.viewpagerproblem.ui.view.Type0View
import com.example.viewpagerproblem.ui.view.Type1View

private const val TYPE_0 = 0
private const val TYPE_1 = 1

class CyclicAdapter(
    val onClickedCallback: (id: String) -> Unit,
    val onCloseClickedCallback: (id: String) -> Unit,
) : ListAdapter<IViewData, RecyclerView.ViewHolder>(DataDiffCallback()) {

    var isCyclic: Boolean = false
        set(value) {
            if (field != value) {
                field = value
            }
        }

    override fun getItemCount(): Int {
        return if (isCyclic) {
            AdapterUtils.MAX_ITEMS // 1000
        } else {
            currentList.size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_0 -> Type0.from(parent)
            TYPE_1 -> Type1.from(parent)
            else -> throw ClassCastException("View Holder for ${viewType} is not specified")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is Type0 -> {
                val item = getItem(
                    AdapterUtils.actualPosition(
                        position,
                        currentList.size
                    )
                ) as ViewData.Type0

                holder.setData(item, onClickedCallback)
            }
            is Type1 -> {
                val item = getItem(
                    AdapterUtils.actualPosition(
                        position,
                        currentList.size
                    )
                ) as ViewData.Type1

                holder.setData(item, onClickedCallback, onCloseClickedCallback)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(AdapterUtils.actualPosition(position, currentList.size))) {
            is ViewData.Type0 -> TYPE_0
            is ViewData.Type1 -> TYPE_1
            else -> throw ClassCastException("View Type for ${item.javaClass} is not specified")
        }
    }

    class Type0 private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(
            viewData: ViewData.Type0,
            onClickedCallback: (id: String) -> Unit
        ) {
            (itemView as Type0View).apply {
                acceptData(viewData)
                setOnClickedCallback {
                    onClickedCallback(viewData.id)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup): Type0 {
                val view = Type0View(parent.context).apply {
                    layoutParams =
                        ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                }
                return Type0(view)
            }
        }
    }

    class Type1 private constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun setData(
            viewData: ViewData.Type1,
            onClickedCallback: (id: String) -> Unit,
            onCloseClickedCallback: (id: String) -> Unit
        ) {
            (itemView as Type1View).apply {
                acceptData(viewData)
                setOnClickedCallback {
                    onClickedCallback(viewData.id)
                }
                setOnCloseClickedCallback(onCloseClickedCallback)
            }
        }

        companion object {
            fun from(parent: ViewGroup): Type1 {
                val view = Type1View(parent.context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                }
                return Type1(view)
            }
        }
    }
}

private class DataDiffCallback : DiffUtil.ItemCallback<IViewData>() {

    override fun areItemsTheSame(oldItem: IViewData, newItem: IViewData): Boolean {
        return when (oldItem) {
            is ViewData.Type0 -> {
                when (newItem) {
                    is ViewData.Type0 -> oldItem.id == newItem.id
                    else -> false
                }
            }
            is ViewData.Type1 -> {
                when (newItem) {
                    is ViewData.Type1 -> oldItem.id == newItem.id
                    else -> false
                }
            }
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: IViewData, newItem: IViewData): Boolean {
        return when (oldItem) {
            is ViewData.Type0 -> {
                when (newItem) {
                    is ViewData.Type0 -> oldItem as ViewData.Type0 == newItem as ViewData.Type0
                    else -> false
                }
            }
            is ViewData.Type1 -> {
                when (newItem) {
                    is ViewData.Type1 -> oldItem as ViewData.Type1 == newItem as ViewData.Type1
                    else -> false
                }
            }
            else -> false
        }
    }
}