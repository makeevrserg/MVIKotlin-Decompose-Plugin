package com.makeevrserg.mvikotlin.intellij.data.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.data.model.BottstrapperType
import com.makeevrserg.mvikotlin.intellij.storage.StorageValue
import com.makeevrserg.mvikotlin.intellij.storage.impl.BooleanStorageValue
import com.makeevrserg.mvikotlin.intellij.storage.impl.InMemoryStorageValue

class StorageApiImpl(private val propertiesComponent: PropertiesComponent) : StorageApi {
    override val useKlibsStorageValue: StorageValue<Boolean> by lazy {
        BooleanStorageValue(
            key = "USE_KLIBS_FACTORY",
            properties = propertiesComponent,
            default = false
        )
    }
    override val createStorePackageStorageValue: StorageValue<Boolean> by lazy {
        BooleanStorageValue(
            key = "CREATE_STORE_PACKAGE",
            properties = propertiesComponent,
            default = false
        )
    }
    override val decomposeMviIntegrationStorageValue: StorageValue<Boolean> by lazy {
        BooleanStorageValue(
            key = "DECOMPOSE_MVI_INTEGRATION",
            properties = propertiesComponent,
            default = false
        )
    }
    override val createBootstrapperStorageValue: StorageValue<BottstrapperType>
        get() = InMemoryStorageValue(
            key = "CREATE_BOOTSTRAPPER",
            initial = BottstrapperType.NONE
        )

    override fun createNameStorageValue(): StorageValue<String> = InMemoryStorageValue(
        key = "NAME",
        initial = ""
    )
}
