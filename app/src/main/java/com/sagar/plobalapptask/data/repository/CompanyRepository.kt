package com.sagar.plobalapptask.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sagar.plobalapptask.data.db.entities.Company
import com.sagar.plobalapptask.data.network.MyApi
import com.sagar.plobalapptask.data.network.SafeApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


private const val MINIMUM_INTERVAL = 6

class CompanyRepository(
    private val api: MyApi
) : SafeApiRequest() {
    private val companies = MutableLiveData<List<Company>>()
    private suspend fun fetchCompanies() {
        val companyResponse = apiRequest { api.companies() }
        companies.postValue(companyResponse.companyList.sortedBy {
            it.data.totalSale.total
        })
    }

    suspend fun getCompanies(): LiveData<List<Company>> {
        return withContext(Dispatchers.IO) {
            fetchCompanies()
            companies
        }
    }


}