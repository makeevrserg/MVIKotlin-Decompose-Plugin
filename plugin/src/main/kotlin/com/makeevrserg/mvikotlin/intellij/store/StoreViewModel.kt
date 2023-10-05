package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.BaseViewModel
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class StoreViewModel(
    private val storageApi: StorageApi,
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    val nameStorageValue = storageApi.createNameStorageValue()
    val useKlibsStorageValue = storageApi.useKlibsStorageValue

    val successFlow = MutableSharedFlow<Unit>()

    private fun createGenericTemplate(name: String) {
        val properties: MutableMap<String, Any> = mutableMapOf(
            nameStorageValue.asPair(),
            useKlibsStorageValue.asPair()
        )
        val fileApi = projectDependencies.generator.generateKt(
            name,
            "${nameStorageValue.value}$name",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreComponents() {
        createGenericTemplate("Store")
        createGenericTemplate("Reducer")
        createGenericTemplate("Executor")
        createGenericTemplate("StoreFactory")
        createGenericTemplate("Bootstrapper")
    }

    fun onOkButtonClick() {
        createStoreComponents()
        scope.launch { successFlow.emit(Unit) }
    }
}
