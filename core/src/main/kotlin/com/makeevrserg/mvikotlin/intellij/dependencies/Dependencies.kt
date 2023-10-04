package com.makeevrserg.mvikotlin.intellij.dependencies

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.swing.Swing

/**
 * Author
 *
 * @see <a href="https://github.com/levinzonr/jetpack-compose-ui-arch-plugin">levinzonr/jetpack-compose-ui-arch-plugin</a>
 */
object Dependencies {
    val ioScope = CoroutineScope(Dispatchers.IO)
    val mainScope = CoroutineScope(Dispatchers.Swing)
}
