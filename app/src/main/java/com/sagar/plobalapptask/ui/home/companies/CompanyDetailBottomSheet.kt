package com.sagar.plobalapptask.ui.home.companies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.databinding.DialogBottomsheetBinding


const val ARG_COMPANY = "arg_company"

class CompanyDetailBottomSheet : BottomSheetDialogFragment() {

    lateinit var binding: DialogBottomsheetBinding
    private var company: Company? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_bottomsheet, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.takeIf { it.containsKey(ARG_COMPANY) }?.apply {
            company = getParcelable(ARG_COMPANY)
        }
        setupView()
    }

    private fun setupView() {
        company?.let {
            binding.run {
                tvCompanyName.text = it.name
                tvCompanySector.text = it.name
                tvSale.setText("${it.currency} ${it.data.totalSale.total}")
                viewPager.apply {
                    adapter = SampleFragmentPagerAdapter(childFragmentManager, it)
                }
                tabLayout.setupWithViewPager(viewPager)

                viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(state: Int) {}

                    override fun onPageScrolled(
                        position: Int,
                        positionOffset: Float,
                        positionOffsetPixels: Int
                    ) {
                    }

                    override fun onPageSelected(position: Int) {
                        when (position) {
                            0 -> {
                                tvTotal.text = "Total Sales"
                                tvSale.setText("${it.currency} ${it.data.totalSale.total}")
                            }
                            1 -> {
                                tvTotal.text = "Total Cards"
                                tvSale.setText("${it.currency} ${it.data.addToCart.total}")
                            }
                            2 -> {
                                tvTotal.text = "Total Downloads"
                                tvSale.setText("${it.currency} ${it.data.downloads.total}")
                            }
                            3 -> {
                                tvTotal.text = "Total Sessions"
                                tvSale.setText("${it.currency} ${it.data.sessions.total}")
                            }
                        }
                    }
                })

                val tabIcons = intArrayOf(
                    R.drawable.ic_outline_sale_24,
                    R.drawable.ic_add_card_outline_24,
                    R.drawable.ic_outline_download_24,
                    R.drawable.ic_session_24
                )

                for (i in tabIcons.indices) {
                    tabLayout.getTabAt(i)?.setIcon(tabIcons[i])
                }

            }

        }


    }

}

