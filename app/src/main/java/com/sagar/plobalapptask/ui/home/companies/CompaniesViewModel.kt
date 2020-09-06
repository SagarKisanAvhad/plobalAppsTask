package com.sagar.plobalapptask.ui.home.companies

import androidx.lifecycle.ViewModel
import com.sagar.plobalapptask.data.repository.CompanyRepository
import com.sagar.plobalapptask.util.lazyDeferred

class CompaniesViewModel(
    val repository: CompanyRepository
) : ViewModel() {

    val companies by lazyDeferred {
        repository.getCompanies()
    }
}