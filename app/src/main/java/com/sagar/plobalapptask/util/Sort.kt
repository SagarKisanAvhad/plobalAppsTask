package com.sagar.plobalapptask.util

object Sort {
    fun partition(arr: LongArray, low: Int, high: Int): Int {
        val pivot = arr[high]
        var i = low - 1
        for (j in low until high) {
            if (arr[j] < pivot) {
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

    fun sort(arr: LongArray, low: Int, high: Int) {
        if (low < high) {
            val pi = partition(arr, low, high)
            sort(arr, low, pi - 1)
            sort(arr, pi + 1, high)
        }
    }

    /* companion object {
         *//* A utility function to print array of size n *//*
        fun printArray(arr: IntArray) {
            val n = arr.size
            for (i in 0 until n) print(arr[i].toString() + " ")
            println()
        }

        // Driver program
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = intArrayOf(10, 7, 8, 9, 1, 5)
            val n = arr.size
            val ob = QuickSort()
            ob.sort(arr, 0, n - 1)
            println("sorted array")
            printArray(arr)
        }
    }*/
}
