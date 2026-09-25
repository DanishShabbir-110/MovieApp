package com.danish.shared.base

interface BaseStates {
    val isLoading: Boolean get() = false
    val errorMessage:String? get() = null
}