package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.BaseViewModel
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComponentViewModel(
    storageApi: StorageApi,
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    val nameStorageValue = storageApi.createNameStorageValue()
    val decomposeMviIntegration = storageApi.decomposeMviIntegrationStorageValue

    val successFlow = MutableSharedFlow<Unit>()

    private fun createGenericTemplate(templateName: String, fileName: String) {
        val properties: MutableMap<String, Any> = mutableMapOf(
            nameStorageValue.asPair(),
            decomposeMviIntegration.asPair()
        )
        val fileApi = projectDependencies.generator.generateKt(
            templateName,
            fileName,
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    fun onOkButtonClick() {
        createGenericTemplate(
            templateName = "DecomposeDefaultComponent",
            fileName = "Default${nameStorageValue.value}Component"
        )
        createGenericTemplate(
            templateName = "DecomposeComponent",
            fileName = "${nameStorageValue.value}Component"
        )
        scope.launch { successFlow.emit(Unit) }
    }
}
