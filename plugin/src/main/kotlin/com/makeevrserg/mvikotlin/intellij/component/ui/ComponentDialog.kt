package com.makeevrserg.mvikotlin.intellij.component.ui

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.component.feature.ComponentStore
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import com.makeevrserg.mvikotlin.intellij.core.Constant
import com.makeevrserg.mvikotlin.intellij.core.CoroutineFeature
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ComponentDialog(
    private val contract: ComponentStore
) : BaseDialog() {
    private val scope = CoroutineFeature.Main()

    init {
        init()
        contract.successFlow
            .onEach { close(0) }
            .launchIn(scope)
    }

    private fun Panel.createLinkGroup() {
        group("Decompose") {
            row {
                link("\uD83D\uDE80 GitHub repo") {
                    BrowserUtil.open(Constant.DECOMPOSE_GIT)
                }
                link("\uD83D\uDC40 See docs") {
                    BrowserUtil.open(Constant.DECOMPOSE_DOCS)
                }
            }
        }
    }

    private fun Panel.createOptionsGroup() {
        group("Options") {
            row {
                checkBox("MVIKotlin integration")
                    .bindSelected(contract.model.enableMviIntegration::value)
            }
            row { comment("Store will be also created with this enabled") }
        }
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New Decompose Component") }
            row {
                textField().focused()
                    .bindText(contract.model.name::value)
                    .horizontalAlign(HorizontalAlign.FILL)
            }
            createOptionsGroup()
            row { comment("Creates a new Decompose Component and it's default implementation") }
            createLinkGroup()
        }
    }

    override fun doOKAction() {
        panel.apply()
        contract.onFinished()
    }
}
