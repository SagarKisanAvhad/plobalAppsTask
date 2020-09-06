package com.sagar.plobalapptask.ui.home.companies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sagar.plobalapptask.data.repository.CompanyRepository

@Suppress("UNCHECKED_CAST")
class CompaniesViewModelFactory(
    private val repository: CompanyRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CompaniesViewModel(repository) as T
    }
}