package com.ntg.budgetapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BudgetApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }


}
