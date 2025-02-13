package com.makeevrserg.mvikotlin.intellij.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.swing.Swing
import kotlin.coroutines.CoroutineContext

interface CoroutineFeature : CoroutineScope {
    class Io : CoroutineFeature {
        override val coroutineContext: CoroutineContext = Dispatchers.IO + SupervisorJob()
    }

    class Main : CoroutineFeature {
        override val coroutineContext: CoroutineContext = Dispatchers.Swing + SupervisorJob()
    }
}
