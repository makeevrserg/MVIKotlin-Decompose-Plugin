package com.makeevrserg.mvikotlin.intellij.data

import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.data.model.ComponentChildType
import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate

interface StorageApi {
    val useKlibsStorageValue: IntellijMutableKrate<Boolean>
    val createStorePackageStorageValue: IntellijMutableKrate<Boolean>
    val decomposeMviIntegrationStorageValue: IntellijMutableKrate<Boolean>
    val createBootstrapperStorageValue: IntellijMutableKrate<BootstrapperType>
    val componentChildTypeStorageValue: IntellijMutableKrate<ComponentChildType>
    fun createNameStorageValue(): IntellijMutableKrate<String>
}
