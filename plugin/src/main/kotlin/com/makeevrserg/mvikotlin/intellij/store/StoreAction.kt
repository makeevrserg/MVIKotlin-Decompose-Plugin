package com.makeevrserg.mvikotlin.intellij.store

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies

class StoreAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val deps = ProjectDependencies(e.project)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = StoreViewModel(directory, ProjectDependencies(e.project))
        StoreDialog(viewModel).show()
    }
}
