package com.makeevrserg.mvikotlin.intellij.data

import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.data.model.ComponentChildType
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue

interface StorageApi {
    val useKlibsStorageValue: StorageValue<Boolean>
    val createStorePackageStorageValue: StorageValue<Boolean>
    val decomposeMviIntegrationStorageValue: StorageValue<Boolean>
    val createBootstrapperStorageValue: StorageValue<BootstrapperType>
    val componentChildTypeStorageValue: StorageValue<ComponentChildType>
    fun createNameStorageValue(): StorageValue<String>
}
