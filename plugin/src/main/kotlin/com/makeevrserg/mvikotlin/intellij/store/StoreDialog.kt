package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bind
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StoreDialog(
    private val contract: StoreContract
) : BaseDialog() {
    init {
        init()
        contract.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    private val BootstrapperType.translation: String
        get() = when (this) {
            BootstrapperType.NONE -> "No Bootstrapper"
            BootstrapperType.SIMPLE -> "Create simple Bootstrapper"
            BootstrapperType.CUSTOM -> "Create custom Bootstrapper"
        }

    private fun Panel.createBootstrapperGroup() {
        buttonsGroup("Bootstrapper") {
            BootstrapperType.values().forEach { bootstrapperType ->
                row {
                    radioButton(
                        text = bootstrapperType.translation,
                        value = bootstrapperType
                    )
                }
            }
        }.bind(contract.model.bootstrapperType::value)
    }

    private fun Panel.createOptionsGroup() {
        group("Options") {
            row {
                checkBox("Use klibs factory")
                    .bindSelected(contract.model.useKlibs::value)
            }
            row {
                checkBox("Create store package")
                    .bindSelected(contract.model.useCreatePackage::value)
            }
        }
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New MVI store") }
            row {
                textField().focused()
                    .bindText(contract.model.name::value)
                    .horizontalAlign(HorizontalAlign.FILL)
            }
            row { comment("Creates a new MVI store with factory, reducer, executor") }
            createOptionsGroup()
            createBootstrapperGroup()
        }
    }

    override fun doOKAction() {
        panel.apply()
        contract.onFinished()
    }
}
