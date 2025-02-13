package com.makeevrserg.mvikotlin.intellij.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface CoroutineFeature : CoroutineScope {
    class Io : CoroutineFeature {
        private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
        override val coroutineContext: CoroutineContext = scope.coroutineContext
    }

    class Main : CoroutineFeature {
        private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
        override val coroutineContext: CoroutineContext = scope.coroutineContext
    }
}
