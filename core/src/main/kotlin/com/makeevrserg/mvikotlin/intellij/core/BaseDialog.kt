package com.makeevrserg.mvikotlin.intellij.core

import com.intellij.openapi.ui.DialogPanel
import com.intellij.openapi.ui.DialogWrapper
import com.makeevrserg.mvikotlin.intellij.dependencies.Dependencies
import javax.swing.JComponent
@Suppress("MaxLineLength")
/**
 * Author
 *
 * @see <a href="https://github.com/levinzonr/jetpack-compose-ui-arch-plugin">levinzonr/jetpack-compose-ui-arch-plugin</a>
 */
abstract class BaseDialog : DialogWrapper(true) {

    lateinit var panel: DialogPanel
    protected val dialogScope = Dependencies.mainScope
    override fun createCenterPanel(): JComponent? {
        panel = createPanel()
        return panel
    }

    abstract fun createPanel(): DialogPanel
}
