package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.core.BaseViewModel
import com.makeevrserg.mvikotlin.intellij.core.PropertyKeys
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class StoreViewModel(
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : BaseViewModel() {
    var name: String = ""
        get() = field.capitalize()

    val successFlow = MutableSharedFlow<Unit>()

    private fun createStoreInterface() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "Store",
            "${name}Store",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreReducer() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "Reducer",
            "${name}Reducer",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreExecutor() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "Executor",
            "${name}Executor",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreStoreFactory() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "StoreFactory",
            "${name}StoreFactory",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    private fun createStoreBootstrapper() {
        val name = "$name"
        val properties: MutableMap<String, Any> = mutableMapOf(PropertyKeys.Name to name)
        val fileApi = projectDependencies.generator.generateKt(
            "Bootstrapper",
            "${name}Bootstrapper",
            directory,
            properties
        )
        projectDependencies.editor.openFile(fileApi.virtualFile, true)
    }

    fun onOkButtonClick() {
        createStoreInterface()
        createStoreReducer()
        createStoreExecutor()
        createStoreStoreFactory()
        createStoreBootstrapper()
        scope.launch { successFlow.emit(Unit) }
    }
}
