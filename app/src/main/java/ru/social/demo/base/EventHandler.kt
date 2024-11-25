package ru.social.demo.base

interface EventHandler<T> {
    fun handle(event: T)
}