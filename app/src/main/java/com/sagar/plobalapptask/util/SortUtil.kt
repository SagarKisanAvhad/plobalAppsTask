package com.sagar.plobalapptask.util

import com.sagar.plobalapptask.data.db.entities.Company

object SortUtil {
    private fun partition(arr: Array<Company>, low: Int, high: Int, flag: Int): Int {
        val pivot = arr[high]
        var i = low - 1

        for (j in low until high) {

            val condition = when (flag) {
                1 -> arr[j].data.totalSale.total < pivot.data.totalSale.total
                2 -> arr[j].data.addToCart.total < pivot.data.addToCart.total
                3 -> arr[j].data.downloads.total < pivot.data.downloads.total
                else -> arr[j].data.sessions.total < pivot.data.sessions.total
            }

            if (condition) {
                i++
                val temp = arr[i]
                arr[i] = arr[j]
                arr[j] = temp
            }
        }

        val temp = arr[i + 1]
        arr[i + 1] = arr[high]
        arr[high] = temp
        return i + 1
    }

    fun sort(arr: Array<Company>, low: Int, high: Int, flag: Int) {
        if (low < high) {
            val pi = partition(arr, low, high, flag)
            sort(arr, low, pi - 1, flag)
            sort(arr, pi + 1, high, flag)
        }
    }
}
