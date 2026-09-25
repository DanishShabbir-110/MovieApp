package com.danish.shared.base

import androidx.navigation3.runtime.NavKey

sealed class BaseEffectsOfBaseViewModel {
    data class NavigateTo(val navKey: NavKey): BaseEffectsOfBaseViewModel()
    data class NavigateAndPopStack(val navKey: NavKey): BaseEffectsOfBaseViewModel()
    data class NavigateBack(val navKey: NavKey?): BaseEffectsOfBaseViewModel()
    data class ShowToast(val message: String): BaseEffectsOfBaseViewModel()
}