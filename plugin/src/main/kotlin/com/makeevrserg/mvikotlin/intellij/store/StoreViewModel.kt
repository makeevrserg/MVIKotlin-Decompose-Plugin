package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.psi.PsiDirectory
import com.intellij.util.application
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
) : BaseViewModel(), StoreContract {

    override val model: StoreContract.Model = StoreContract.Model(
        name = storageApi.createNameStorageValue(),
        useCreatePackage = storageApi.createStorePackageStorageValue,
        useKlibs = storageApi.useKlibsStorageValue,
        bootstrapperType = storageApi.createBootstrapperStorageValue
    )

    override val successFlow = MutableSharedFlow<Unit>()

    private val properties: MutableMap<String, Any>
        get() = mutableMapOf(
            model.name.asPair(),
            model.useKlibs.asPair(),
            model.bootstrapperType.asPair()
        )

    private fun createOrGetStoreDirectory(): PsiDirectory {
        if (!model.useCreatePackage.value) return directory
        val foundStoreDirectory = directory.findSubdirectory("store")
        if (foundStoreDirectory != null) return foundStoreDirectory
        return application.runWriteAction<PsiDirectory> {
            directory.createSubdirectory("store")
        }
    }

    private fun createGenericTemplate(templateName: String) {
        val directory = createOrGetStoreDirectory()
        val fileApi = projectDependencies.generator.generateKt(
            templateName = templateName,
            fileName = "${model.name.value}$templateName",
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
        if (model.bootstrapperType.value == BottstrapperType.CUSTOM) {
            createGenericTemplate("Bootstrapper")
        }
    }

    override fun onFinished() {
        createStoreComponents()
        scope.launch { successFlow.emit(Unit) }
    }
}
