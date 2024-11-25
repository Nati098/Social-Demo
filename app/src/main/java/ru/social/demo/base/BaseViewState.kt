package ru.social.demo.base

sealed class BaseViewState<out T: Any> {

    data class Success<out T: Any>(val data: List<T>) : BaseViewState<T>()
    object Loading : BaseViewState<Nothing>()
    object Error : BaseViewState<Nothing>()

}