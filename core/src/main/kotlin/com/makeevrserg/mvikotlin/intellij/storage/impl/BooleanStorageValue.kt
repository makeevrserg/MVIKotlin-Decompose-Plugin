package com.makeevrserg.mvikotlin.intellij.storage.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue

class BooleanStorageValue(
    override val key: String,
    private val properties: PropertiesComponent,
    private val default: Boolean
) : StorageValue<Boolean> {
    override var value: Boolean
        get() = properties.getBoolean(key, default)
        set(value) {
            properties.setValue(key, value)
        }
}
