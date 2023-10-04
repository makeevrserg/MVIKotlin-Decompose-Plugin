package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.BaseViewModel
import com.makeevrserg.mvikotlin.intellij.core.PropertyKeys
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ComponentViewModel(
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    var name: String = ""
        get() = field.capitalize()

    val successFlow = MutableSharedFlow<Unit>()

    private fun createApiComponent() {
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "DecomposeComponent",
            "${name}Component",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createDefaultComponent() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "DecomposeDefaultComponent",
            "Default${name}Component",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    fun onOkButtonClick() {
        createApiComponent()
        createDefaultComponent()
        scope.launch { successFlow.emit(Unit) }
    }
}
