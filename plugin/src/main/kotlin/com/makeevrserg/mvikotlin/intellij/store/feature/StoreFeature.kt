package com.makeevrserg.mvikotlin.intellij.store.feature

import com.intellij.psi.PsiDirectory
import com.intellij.util.application
import com.makeevrserg.mvikotlin.intellij.core.CoroutineFeature
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class StoreFeature(
    storageApi: StorageApi,
    private val directory: PsiDirectory,
    private val projectDependencies: ProjectDependencies
) : CoroutineFeature by CoroutineFeature.Main(), StoreStore {

    override val model: StoreStore.Model = StoreStore.Model(
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
        if (!model.useCreatePackage.kValue) return directory
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
            fileName = "${model.name.kValue}$templateName",
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
        if (model.bootstrapperType.kValue == BootstrapperType.CUSTOM) {
            createGenericTemplate("Bootstrapper")
        }
    }

    override fun onFinished() {
        createStoreComponents()
        launch { successFlow.emit(Unit) }
    }
}
