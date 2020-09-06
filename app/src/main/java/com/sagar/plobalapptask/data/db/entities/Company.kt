package com.sagar.plobalapptask.data.db.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TotalSale(
    @SerializedName("total") @JvmField val total: Long,
    @SerializedName("month_wise") @JvmField val monthWise: MonthWise
) : Parcelable

@Parcelize
data class CompanyDetails(
    @SerializedName("sessions") @JvmField val sessions: Sessions,
    @SerializedName("downloads") @JvmField val downloads: Downloads,
    @SerializedName("total_sale") @JvmField val totalSale: TotalSale,
    @SerializedName("add_to_cart") @JvmField val addToCart: AddToCart
) : Parcelable


@Parcelize
data class Company(
    @SerializedName("data") @JvmField val data: CompanyDetails,
    @SerializedName("name") @JvmField val name: String,
    @SerializedName("currency") @JvmField val currency: String,
    @SerializedName("money_format") @JvmField val moneyFormat: String
) : Parcelable

@Parcelize
data class MonthWise(
    @SerializedName("feb") @JvmField val feb: Long,
    @SerializedName("apr") @JvmField val apr: Long,
    @SerializedName("jun") @JvmField val jun: Long,
    @SerializedName("may") @JvmField val may: Long,
    @SerializedName("jan") @JvmField val jan: Long,
    @SerializedName("mar") @JvmField val mar: Long
) : Parcelable

@Parcelize
data class Sessions(
    @SerializedName("total") @JvmField val total: Long,
    @SerializedName("month_wise") @JvmField val monthWise: MonthWise
) : Parcelable

@Parcelize
data class AddToCart(
    @SerializedName("total") @JvmField val total: Long,
    @SerializedName("month_wise") @JvmField val monthWise: MonthWise
) : Parcelable

@Parcelize
data class Downloads(
    @SerializedName("total") @JvmField val total: Long,
    @SerializedName("month_wise") @JvmField val monthWise: MonthWise
) : Parcelable
