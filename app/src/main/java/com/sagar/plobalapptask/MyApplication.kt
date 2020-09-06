package com.sagar.plobalapptask

import android.app.Application
import com.sagar.plobalapptask.data.network.MyApi
import com.sagar.plobalapptask.data.network.NetworkConnectionInterceptor
import com.sagar.plobalapptask.data.repository.CompanyRepository
import com.sagar.plobalapptask.ui.home.companies.CompaniesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MyApplication))
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { CompanyRepository(instance()) }
        bind() from provider { CompaniesViewModelFactory(instance()) }

    }
}