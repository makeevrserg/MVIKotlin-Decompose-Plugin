package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComponentDialog(
    private val viewModel: ComponentViewModel
) : BaseDialog() {
    init {
        init()
        viewModel.successFlow
            .onEach { close(0) }
            .launchIn(dialogScope)
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Decompose Component") }
            row {
                textField().focused().bindText(viewModel.nameStorageValue::value).horizontalAlign(
                    HorizontalAlign.FILL
                )
            }
            row { comment("Creates a new Decompose Component and it's default implementation") }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}
