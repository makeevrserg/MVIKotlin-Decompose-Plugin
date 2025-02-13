package com.makeevrserg.mvikotlin.intellij.component.feature

import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import kotlinx.coroutines.flow.Flow

interface ComponentStore {
    val model: Model

    val successFlow: Flow<Unit>

    fun onFinished()

    class Model(
        val name: IntellijMutableKrate<String>,
        val enableMviIntegration: IntellijMutableKrate<Boolean>
    )
}
