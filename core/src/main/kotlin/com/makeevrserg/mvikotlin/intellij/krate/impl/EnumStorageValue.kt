package com.makeevrserg.mvikotlin.intellij.krate.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import ru.astrainteractive.klibs.kstorage.api.MutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate
import kotlin.enums.EnumEntries

class EnumStorageValue<T : Enum<T>>(
    override val key: String,
    private val properties: PropertiesComponent,
    initial: T,
    entries: EnumEntries<T>
) : IntellijMutableKrate<T>,
    MutableKrate<T> by DefaultMutableKrate(
        factory = { initial },
        saver = {
            properties.setValue(key, initial.ordinal.toString())
        },
        loader = {
            val index = properties.getValue(key)?.toIntOrNull() ?: 0
            entries.getOrNull(index) ?: entries.first()
        }
    )
