package com.makeevrserg.mvikotlin.intellij.component

import com.makeevrserg.mvikotlin.intellij.storage.StorageValue
import kotlinx.coroutines.flow.Flow

interface ComponentContract {
    val model: Model

    val successFlow: Flow<Unit>

    fun onFinished()

    class Model(
        val name: StorageValue<String>,
        val enableMviIntegration: StorageValue<Boolean>
    )
}
