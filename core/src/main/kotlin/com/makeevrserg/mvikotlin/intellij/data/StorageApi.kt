package com.makeevrserg.mvikotlin.intellij.data

import com.makeevrserg.mvikotlin.intellij.storage.StorageValue

interface StorageApi {
    val useKlibsStorageValue: StorageValue<Boolean>
    fun createNameStorageValue(): StorageValue<String>
}
