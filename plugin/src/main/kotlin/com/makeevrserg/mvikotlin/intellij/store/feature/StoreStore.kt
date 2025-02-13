package com.makeevrserg.mvikotlin.intellij.store.feature

import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import kotlinx.coroutines.flow.Flow

interface StoreStore {
    val model: Model

    val successFlow: Flow<Unit>

    fun onFinished()

    class Model(
        val name: IntellijMutableKrate<String>,
        val useCreatePackage: IntellijMutableKrate<Boolean>,
        val useKlibs: IntellijMutableKrate<Boolean>,
        val bootstrapperType: IntellijMutableKrate<BootstrapperType>
    )
}
