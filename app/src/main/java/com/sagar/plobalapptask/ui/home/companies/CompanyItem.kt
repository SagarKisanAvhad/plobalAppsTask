package com.sagar.plobalapptask.ui.home.companies

import com.sagar.plobalapptask.R
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.databinding.ItemCompanyBinding
import com.xwray.groupie.databinding.BindableItem

class CompanyItem(
    private val company: Company
) : BindableItem<ItemCompanyBinding>() {

    override fun getLayout(): Int {
        return R.layout.item_company
    }

    override fun bind(viewBinding: ItemCompanyBinding, position: Int) {
        viewBinding.company = company
        viewBinding.tvSale.text = viewBinding.root.context.getString(
            R.string.total_value,
            company.currency,
            company.data.totalSale.total
        )
    }

    fun getCompany() = company

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as CompanyItem
        if (company != other.company) return false
        return true
    }

    override fun hashCode(): Int {
        return company.hashCode()
    }
}