package com.makeevrserg.mvikotlin.intellij.data.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.core.PropertyKeys
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue
import com.makeevrserg.mvikotlin.intellij.storage.impl.BooleanStorageValue
import com.makeevrserg.mvikotlin.intellij.storage.impl.InMemoryStorageValue

class StorageApiImpl(private val propertiesComponent: PropertiesComponent) : StorageApi {
    override val useKlibsStorageValue: StorageValue<Boolean> by lazy {
        BooleanStorageValue(
            key = PropertyKeys.USE_KLIBS_FACTORY,
            properties = propertiesComponent,
            default = false
        )
    }

    override fun createNameStorageValue(): StorageValue<String> = InMemoryStorageValue(
        key = PropertyKeys.Name,
        initial = ""
    )
}
