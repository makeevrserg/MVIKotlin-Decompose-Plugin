package com.makeevrserg.mvikotlin.intellij.storage.impl

import com.makeevrserg.mvikotlin.intellij.storage.StorageValue

class InMemoryStorageValue<T>(
    override val key: String,
    initial: T
) : StorageValue<T> {
    private var _value: T = initial
    override var value: T
        get() = _value
        set(value) {
            _value = value
        }
}
