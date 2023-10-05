package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bind
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import com.makeevrserg.mvikotlin.intellij.data.model.BottstrapperType
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StoreDialog(
    private val viewModel: StoreViewModel
) : BaseDialog() {
    init {
        init()
        viewModel.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New MVI store") }
            row {
                textField().focused().bindText(viewModel.nameStorageValue::value).horizontalAlign(
                    HorizontalAlign.FILL
                )
            }
            row { comment("Creates a new MVI store with factory, reducer, executor") }
            group("Options") {
                row {
                    checkBox("Use klibs factory")
                        .bindSelected(viewModel.useKlibsStorageValue::value)
                }
                row {
                    checkBox("Create store package")
                        .bindSelected(viewModel.createPackageStorageValue::value)
                }
            }
            buttonsGroup("Bootstrapper") {
                row {
                    radioButton(
                        "No Bootstrapper",
                        BottstrapperType.NONE
                    )
                }
                row {
                    radioButton(
                        "Create custom Bootstrapper",
                        BottstrapperType.CUSTOM
                    )
                }
                row {
                    radioButton(
                        "Create simple Bootstrapper",
                        BottstrapperType.SIMPLE
                    )
                }
            }.bind(viewModel.createBootstrapperStorageValue::value)
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}
