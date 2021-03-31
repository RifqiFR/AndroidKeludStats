package com.keludstats.shared.callback

interface RequestCallback<T> {
    fun requestSuccess(data: T)
    fun requestError(message: String?)
}