package com.example.viewpagerproblem.adapter

object AdapterUtils {
    const val MAX_ITEMS = 1000

    fun actualPosition(position: Int, listSize: Int): Int {
        return if (listSize == 0) {
            0
        } else {
            (position + listSize) % listSize
        }
    }

    fun getCyclicInitialPosition(listSize: Int): Int {
        return if (listSize > 0) {
            MAX_ITEMS / 2 - ((MAX_ITEMS / 2) % listSize)
        } else {
            0
        }
    }
}