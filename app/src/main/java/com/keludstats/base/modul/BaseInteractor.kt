package com.keludstats.base.modul

import com.keludstats.shared.retrofit.ServiceGenerator

abstract class BaseInteractor<T>(private val serviceClass: Class<T> ) {
    protected val service: T get() = ServiceGenerator.createService(serviceClass)
}