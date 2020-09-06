package com.sagar.plobalapptask.ui.home.companies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.databinding.CompaniesFragmentBinding
import com.sagar.plobalapptask.util.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class CompaniesFragment : Fragment(), KodeinAware, SortingBottomSheet.SortClickListener {
    override val kodein by kodein()
    private val factory: CompaniesViewModelFactory by instance()
    private val viewModel: CompaniesViewModel by viewModels { factory }
    private lateinit var binding: CompaniesFragmentBinding
    private lateinit var section: Section
    private var companyList: List<Company>? = null
    private var sortedBy: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.companies_fragment, container, false)
        bindUi()
        return binding.root
    }

    private fun bindUi() {
        section = Section()
        binding.tvSort.setOnClickListener {
            SortingBottomSheet.newInstance().apply {
                setSortClickListener(this@CompaniesFragment, sortedBy)
            }.show(childFragmentManager, "Sorting Bottom sheet")
        }

        Coroutines.main {
            binding.progressBar.show()
            viewModel.companies.await().observe(viewLifecycleOwner, Observer { companies ->
                binding.progressBar.hide()
                companyList = companies
                initRecyclerView(companies.mapToCompanyItems())
                binding.tvSort.visible()
            })
        }
    }


    private fun initRecyclerView(companyItems: List<CompanyItem>) {

        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(section)
            setOnItemClickListener { item, view ->
                if (item is CompanyItem) {
                    CompanyDetailBottomSheet().apply {
                        arguments = Bundle().apply {
                            putParcelable(ARG_COMPANY, item.getCompany())
                        }
                    }
                        .show(childFragmentManager, "Company Bottomsheet")
                }

            }
        }

        binding.rvCompany.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        section.addAll(companyItems)

    }

    private fun List<Company>.mapToCompanyItems() = map { CompanyItem(it) }

    override fun onSortClick(flag: Int) {
        if (sortedBy != flag) {
            companyList?.let {
                val companyArray = it.toTypedArray()
                SortUtil.sort(companyArray, 0, companyArray.lastIndex, flag)
                val items = companyArray.toList().mapToCompanyItems()
                section.update(items)
                sortedBy = flag
            }
        }
    }
}

