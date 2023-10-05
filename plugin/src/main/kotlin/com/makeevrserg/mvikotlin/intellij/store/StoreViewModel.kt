package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.BaseViewModel
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.data.model.BottstrapperType
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class StoreViewModel(
    storageApi: StorageApi,
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    val nameStorageValue = storageApi.createNameStorageValue()
    val createPackageStorageValue = storageApi.createStorePackageStorageValue
    val useKlibsStorageValue = storageApi.useKlibsStorageValue
    val createBootstrapperStorageValue = storageApi.createBootstrapperStorageValue

    val successFlow = MutableSharedFlow<Unit>()

    private fun createGenericTemplate(name: String) {
        val properties: MutableMap<String, Any> = mutableMapOf(
            nameStorageValue.asPair(),
            useKlibsStorageValue.asPair(),
            createBootstrapperStorageValue.asPair()
        )

        val directory = directory.takeIf { !createPackageStorageValue.value } ?: let {
            directory.findSubdirectory("store") ?: directory.createSubdirectory("store")
        }
        val fileApi = projectDependencies.generator.generateKt(
            templateName = name,
            fileName = "${nameStorageValue.value}$name",
            directory = directory,
            properties = properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreComponents() {
        createGenericTemplate("Store")
        createGenericTemplate("Reducer")
        createGenericTemplate("Executor")
        createGenericTemplate("StoreFactory")
        if (createBootstrapperStorageValue.value == BottstrapperType.CUSTOM) {
            createGenericTemplate("Bootstrapper")
        }
    }

    fun onOkButtonClick() {
        createStoreComponents()
        scope.launch { successFlow.emit(Unit) }
    }
}
