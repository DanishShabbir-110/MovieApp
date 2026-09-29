package com.danish.search_api

import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi

interface SearchFeatureApi: FeatureApi {
    fun searchEntry(): NavKey
}