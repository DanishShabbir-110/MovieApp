package com.danish.main_api

import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi

interface MainFeatureApi : FeatureApi {
    fun mainEntry(): NavKey
}