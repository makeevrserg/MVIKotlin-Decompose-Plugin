package com.makeevrserg.mvikotlin.intellij.store

import com.makeevrserg.mvikotlin.intellij.data.model.BottstrapperType
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue
import kotlinx.coroutines.flow.Flow

interface StoreContract {
    val model: Model

    val successFlow: Flow<Unit>

    fun onFinished()

    class Model(
        val name: StorageValue<String>,
        val useCreatePackage: StorageValue<Boolean>,
        val useKlibs: StorageValue<Boolean>,
        val bootstrapperType: StorageValue<BottstrapperType>
    )
}
