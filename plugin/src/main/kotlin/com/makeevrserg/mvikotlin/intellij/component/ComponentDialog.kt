package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComponentDialog(
    private val contract: ComponentContract
) : BaseDialog() {
    init {
        init()
        contract.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Decompose Component") }
            row {
                textField().focused().bindText(contract.model.name::value).horizontalAlign(
                    HorizontalAlign.FILL
                )
            }
            group("Options") {
                row {
                    checkBox("MVIKotlin integration")
                        .bindSelected(contract.model.enableMviIntegration::value)
                }
                row { comment("Store will be also created with this enabled") }
            }
            row { comment("Creates a new Decompose Component and it's default implementation") }
        }
    }

    override fun doOKAction() {
        panel.apply()
        contract.onFinished()
    }
}
