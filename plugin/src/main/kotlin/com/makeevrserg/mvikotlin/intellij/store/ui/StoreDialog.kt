package com.makeevrserg.mvikotlin.intellij.store.ui

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.ui.DialogPanel
import com.intellij.ui.dsl.builder.Panel
import com.intellij.ui.dsl.builder.bind
import com.intellij.ui.dsl.builder.bindSelected
import com.intellij.ui.dsl.builder.bindText
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.dsl.gridLayout.HorizontalAlign
import com.makeevrserg.mvikotlin.intellij.core.BaseDialog
import com.makeevrserg.mvikotlin.intellij.core.Constant
import com.makeevrserg.mvikotlin.intellij.core.CoroutineFeature
import com.makeevrserg.mvikotlin.intellij.data.model.BootstrapperType
import com.makeevrserg.mvikotlin.intellij.store.feature.StoreStore
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class StoreDialog(
    private val contract: StoreStore
) : BaseDialog() {
    private val scope = CoroutineFeature.Main()

    init {
        init()
        contract.successFlow
            .onEach { close(0) }
            .launchIn(scope)
    }

    private val BootstrapperType.translation: String
        get() = when (this) {
            BootstrapperType.NONE -> "No Bootstrapper"
            BootstrapperType.SIMPLE -> "Create simple Bootstrapper"
            BootstrapperType.CUSTOM -> "Create custom Bootstrapper"
        }

    private fun Panel.createBootstrapperGroup() {
        buttonsGroup("Bootstrapper") {
            BootstrapperType.entries.forEach { bootstrapperType ->
                row {
                    radioButton(
                        text = bootstrapperType.translation,
                        value = bootstrapperType
                    )
                }
            }
        }.bind(contract.model.bootstrapperType::kValue)
    }

    private fun Panel.createOptionsGroup() {
        group("Options") {
            row {
                checkBox("Use klibs factory")
                    .bindSelected(contract.model.useKlibs::kValue)
            }
            row {
                checkBox("Create store package")
                    .bindSelected(contract.model.useCreatePackage::kValue)
            }
        }
    }

    private fun Panel.createLinkGroup() {
        group("MVIKotlin") {
            row {
                link("\uD83D\uDE80 GitHub repo") {
                    BrowserUtil.open(Constant.MVIKOTLIN_GIT)
                }
                link("\uD83D\uDC40 See docs") {
                    BrowserUtil.open(Constant.MVIKOTLIN_DOCS)
                }
            }
        }
    }

    override fun createPanel(): DialogPanel {
        return panel {
            row { label("New MVI store") }
            row {
                textField().focused()
                    .bindText(contract.model.name::kValue)
                    .horizontalAlign(HorizontalAlign.FILL)
            }
            row { comment("Creates a new MVI store with factory, reducer, executor") }
            createOptionsGroup()
            createBootstrapperGroup()
            createLinkGroup()
        }
    }

    override fun doOKAction() {
        panel.apply()
        contract.onFinished()
    }
}
