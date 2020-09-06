package com.sagar.plobalapptask.data.network.responses

import com.google.gson.annotations.SerializedName
import com.sagar.plobalapptask.data.db.entities.Company

data class CompanyResponse(
    @SerializedName("apps") @JvmField val companyList: List<Company>
) {
    @JvmField
    val isSuccessFul: Boolean = companyList.isNotEmpty()
}

