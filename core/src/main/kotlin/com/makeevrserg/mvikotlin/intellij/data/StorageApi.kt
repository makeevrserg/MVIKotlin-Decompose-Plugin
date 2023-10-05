package com.makeevrserg.mvikotlin.intellij.data

import com.makeevrserg.mvikotlin.intellij.data.model.BottstrapperType
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue

interface StorageApi {
    val useKlibsStorageValue: StorageValue<Boolean>
    val createBootstrapperStorageValue: StorageValue<BottstrapperType>
    fun createNameStorageValue(): StorageValue<String>
}
