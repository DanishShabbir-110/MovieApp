package com.danish.home_api

import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi

interface HomeFeatureAPi: FeatureApi {

    fun homeEntry(): NavKey
}