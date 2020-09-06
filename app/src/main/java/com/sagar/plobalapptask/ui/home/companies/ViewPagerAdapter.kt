package com.sagar.plobalapptask.ui.home.companies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sagar.plobalapptask.data.db.entities.Company

class ViewPagerAdapter(fragment: Fragment, val company: Company) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        val fragment = ChartFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(ARG_COMPANY_DATA, company)
        }
        return fragment
    }
}