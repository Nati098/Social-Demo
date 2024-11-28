package ru.social.demo.base

interface BaseEvent

interface BaseViewState

interface EventHandler<T: BaseEvent> {
    fun handle(event: T)
}