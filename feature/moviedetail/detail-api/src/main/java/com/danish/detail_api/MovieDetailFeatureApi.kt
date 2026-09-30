package com.danish.detail_api

import androidx.navigation3.runtime.NavKey
import com.danish.navigation.FeatureApi

interface MovieDetailFeatureApi : FeatureApi{
    fun movieDetailEntry(movieId: Int): NavKey
}