package com.makeevrserg.mvikotlin.intellij.storage

interface StorageValue<T> {
    val key: String

    var value: T

    fun asPair(): Pair<String, T> {
        return key to value
    }
}
