package de.niklasenglmeier.weatherapp.services

import java.lang.Exception

interface OnResultListener<T> {
    fun onSuccess(result: T)
    fun onFailure(exception: Exception)
}