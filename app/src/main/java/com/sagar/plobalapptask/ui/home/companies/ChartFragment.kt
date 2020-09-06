package com.sagar.plobalapptask.ui.home.companies

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.databinding.FragmentPageBinding


const val ARG_COMPANY_DATA = "arg_company_data"
private const val ARG_ = "arg_company_data"


class ChartFragment : Fragment() {
    private var company: Company? = null
    private lateinit var binding: FragmentPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page, container, false)
        arguments?.takeIf { it.containsKey(ARG_COMPANY_DATA) }?.apply {
            company = getParcelable(ARG_COMPANY_DATA)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_COMPANY_DATA) }?.apply {
            company = getParcelable(ARG_COMPANY_DATA)
        }
        plotLineChart()
    }

    private fun plotLineChart() {
        company?.let {
            val current =
                (parentFragment as? CompanyDetailBottomSheet)?.binding?.viewPager?.currentItem
            val monthData = when (current) {
                0 -> it.data.totalSale.monthWise
                1 -> it.data.addToCart.monthWise
                2 -> it.data.downloads.monthWise
                else -> it.data.sessions.monthWise
            }

            val xAxisValues = arrayListOf(
                "Jan", "Feb", "Mar", "Apr", "May", "Jun"
            )

            val yAxisValues = arrayListOf(
                Entry(monthData.jan.toFloat(), 0),
                Entry(monthData.feb.toFloat(), 1),
                Entry(monthData.mar.toFloat(), 2),
                Entry(monthData.apr.toFloat(), 3),
                Entry(monthData.may.toFloat(), 4),
                Entry(monthData.jun.toFloat(), 5)
            )

            val lineDataSet = LineDataSet(yAxisValues, "").apply {
                fillAlpha = 110
                color = Color.BLACK
                setCircleColor(Color.BLACK)
                lineWidth = 1F
                valueTextSize = 9F
                circleSize = 2F
                setDrawCircleHole(false)
                setDrawFilled(true)
                setDrawValues(false)
            }
            val listOfLineDataSet = listOf(lineDataSet)
            binding.chart.apply {
                axisLeft.isEnabled = true
                axisRight.isEnabled = true
                xAxis.isEnabled = true
                animateY(1000)
                setDescription("")
                xAxis.position = XAxis.XAxisPosition.BOTTOM
                setDrawGridBackground(true)
                xAxis.setDrawAxisLine(true)
                data = LineData(xAxisValues, listOfLineDataSet)
            }
            binding.chart.legend.form = Legend.LegendForm.LINE
        }
    }
}