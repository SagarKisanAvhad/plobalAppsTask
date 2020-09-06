package com.sagar.plobalapptask.data.network

import com.sagar.plobalapptask.data.network.responses.CompanyResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MyApi {

    @GET("dummy-app-data.json")
    suspend fun companies(): Response<CompanyResponse>

    companion object {
        operator fun invoke(networkInterceptor: NetworkConnectionInterceptor): MyApi {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://plobalapps.s3-ap-southeast-1.amazonaws.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}