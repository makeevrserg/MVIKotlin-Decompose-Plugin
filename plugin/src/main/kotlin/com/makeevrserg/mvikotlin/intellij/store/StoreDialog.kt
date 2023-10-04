package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
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
            row { textField().focused().bindText(viewModel::name).horizontalAlign(HorizontalAlign.FILL) }
            row { comment("Creates a new MVI store with factory, reducer, executor") }
        }
    }

    override fun doOKAction() {
        panel.apply()
        viewModel.onOkButtonClick()
    }
}
