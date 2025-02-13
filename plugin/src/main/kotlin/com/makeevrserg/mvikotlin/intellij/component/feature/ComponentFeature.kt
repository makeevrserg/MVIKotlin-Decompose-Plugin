package com.makeevrserg.mvikotlin.intellij.component.feature

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.CoroutineFeature
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComponentFeature(
    storageApi: StorageApi,
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : CoroutineFeature by CoroutineFeature.Main(), ComponentStore {
    override val model: ComponentStore.Model = ComponentStore.Model(
        name = storageApi.createNameStorageValue(),
        enableMviIntegration = storageApi.decomposeMviIntegrationStorageValue
    )
    override val successFlow = MutableSharedFlow<Unit>()

    private val properties: MutableMap<String, Any>
        get() = mutableMapOf(
            model.name.asPair(),
            model.enableMviIntegration.asPair()
        )

    private fun createGenericTemplate(templateName: String, fileName: String) {
        val fileApi = projectDependencies.generator.generateKt(
            templateName,
            fileName,
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    override fun onFinished() {
        createGenericTemplate(
            templateName = "DecomposeDefaultComponent",
            fileName = "Default${model.name.value}Component"
        )
        createGenericTemplate(
            templateName = "DecomposeComponent",
            fileName = "${model.name.value}Component"
        )
        launch { successFlow.emit(Unit) }
    }
}
