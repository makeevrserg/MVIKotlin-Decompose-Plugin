package com.makeevrserg.mvikotlin.intellij.krate

import ru.astrainteractive.klibs.kstorage.api.MutableKrate

interface IntellijMutableKrate<T> : MutableKrate<T> {
    val key: String

    var value: T
        get() = loadAndGet()
        set(value) {
            save(value)
        }

    fun asPair(): Pair<String, T> {
        return key to value
    }
}
