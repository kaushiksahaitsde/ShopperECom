package com.example.shopper

import android.app.Application
import com.example.data.di.dataModule
import com.example.domain.di.domainModule
import com.example.shopper.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class ShopperApp:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@ShopperApp)
            modules(listOf(
                dataModule,
                domainModule,
                presentationModule
            ))
        }
    }
}