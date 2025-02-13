package com.makeevrserg.mvikotlin.intellij.data.impl

import com.intellij.ide.util.PropertiesComponent
import com.makeevrserg.mvikotlin.intellij.data.StorageApi
import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.data.model.ComponentChildType
import com.makeevrserg.mvikotlin.intellij.krate.IntellijMutableKrate
import com.makeevrserg.mvikotlin.intellij.krate.impl.BooleanStorageValue
import com.makeevrserg.mvikotlin.intellij.krate.impl.EnumStorageValue
import com.makeevrserg.mvikotlin.intellij.krate.impl.inMemoryStorageValue

class StorageApiImpl(private val propertiesComponent: PropertiesComponent) : StorageApi {
    override val useKlibsStorageValue: IntellijMutableKrate<Boolean> by lazy {
        BooleanStorageValue(
            key = "USE_KLIBS_FACTORY",
            properties = propertiesComponent,
            default = false
        )
    }
    override val createStorePackageStorageValue: IntellijMutableKrate<Boolean> by lazy {
        BooleanStorageValue(
            key = "CREATE_STORE_PACKAGE",
            properties = propertiesComponent,
            default = false
        )
    }
    override val decomposeMviIntegrationStorageValue: IntellijMutableKrate<Boolean> by lazy {
        BooleanStorageValue(
            key = "DECOMPOSE_MVI_INTEGRATION",
            properties = propertiesComponent,
            default = false
        )
    }
    override val createBootstrapperStorageValue: IntellijMutableKrate<BootstrapperType>
        get() = EnumStorageValue(
            key = "CREATE_BOOTSTRAPPER",
            initial = BootstrapperType.NONE,
            properties = propertiesComponent,
            entries = BootstrapperType.entries
        )
    override val componentChildTypeStorageValue: IntellijMutableKrate<ComponentChildType>
        get() = EnumStorageValue(
            key = "COMPONENT_CHILD_TYPE",
            initial = ComponentChildType.NONE,
            properties = propertiesComponent,
            entries = ComponentChildType.entries
        )

    override fun createNameStorageValue(): IntellijMutableKrate<String> = inMemoryStorageValue(
        key = "NAME",
        default = "",
    )
}
