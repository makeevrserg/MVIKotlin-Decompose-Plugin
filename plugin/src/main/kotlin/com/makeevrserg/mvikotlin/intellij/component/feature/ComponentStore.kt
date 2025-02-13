package com.makeevrserg.mvikotlin.intellij.component.feature

import com.makeevrserg.mvikotlin.intellij.storage.StorageValue
import kotlinx.coroutines.flow.Flow

interface ComponentStore {
    val model: Model

    val successFlow: Flow<Unit>

    fun onFinished()

    class Model(
        val name: StorageValue<String>,
        val enableMviIntegration: StorageValue<Boolean>
    )
}
