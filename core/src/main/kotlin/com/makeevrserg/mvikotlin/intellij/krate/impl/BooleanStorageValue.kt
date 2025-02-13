package com.makeevrserg.mvikotlin.intellij.krate.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import ru.astrainteractive.klibs.kstorage.api.MutableKrate
import ru.astrainteractive.klibs.kstorage.api.impl.DefaultMutableKrate

class BooleanStorageValue(
    override val key: String,
    private val properties: PropertiesComponent,
    private val default: Boolean
) : IntellijMutableKrate<Boolean>,
    MutableKrate<Boolean> by DefaultMutableKrate(
        factory = { default },
        saver = { properties.setValue(key, it) },
        loader = { properties.getBoolean(key, default) }
    )
