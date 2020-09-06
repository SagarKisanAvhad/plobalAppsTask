package com.sagar.plobalapptask.ui.home.companies

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sagar.plobalapptask.data.db.entities.Company


class SampleFragmentPagerAdapter(fm: FragmentManager, val company: Company) :
    FragmentPagerAdapter(fm) {
   private val PAGE_COUNT = 4

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getItem(position: Int): Fragment {
        val fragment = ChartFragment()
        fragment.arguments = Bundle().apply {
            putParcelable(ARG_COMPANY_DATA, company)
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        // Generate title based on item position
        return null
    }


}