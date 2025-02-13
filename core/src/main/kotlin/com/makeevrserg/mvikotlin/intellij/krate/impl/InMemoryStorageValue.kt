package com.makeevrserg.mvikotlin.intellij.krate.impl

import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import ru.astrainteractive.klibs.kstorage.api.MutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

fun <T> inMemoryStorageValue(
    key: String,
    default: T
): IntellijMutableKrate<T> {
    var value = default
    return object :
        IntellijMutableKrate<T>,
        MutableKrate<T> by DefaultMutableKrate(
            factory = { value },
            saver = { newValue -> value = newValue },
            loader = { value }
        ) {
        override val key: String = key
    }
}
