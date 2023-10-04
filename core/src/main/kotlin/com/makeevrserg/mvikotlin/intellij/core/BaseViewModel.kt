package com.makeevrserg.mvikotlin.intellij.core

import com.makeevrserg.mvikotlin.intellij.dependencies.Dependencies

/**
 * Author
 *
 * @see <a href="https://github.com/levinzonr/jetpack-compose-ui-arch-plugin">levinzonr/jetpack-compose-ui-arch-plugin</a>
 */
abstract class BaseViewModel {
    protected val scope = Dependencies.ioScope
}
