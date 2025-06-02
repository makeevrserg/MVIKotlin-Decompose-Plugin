package com.makeevrserg.mvikotlin.intellij.krate

import ru.astrainteractive.klibs.kstorage.api.MutableKrate

interface IntellijMutableKrate<T> : MutableKrate<T> {
    val key: String

    var kValue: T
        get() = getValue()
        set(value) {
            save(value)
        }

    fun asPair(): Pair<String, T> {
        return key to kValue
    }
}
