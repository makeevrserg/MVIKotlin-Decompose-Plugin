package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies

class ComponentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val deps = ProjectDependencies(e.project)
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val viewModel = ComponentViewModel(directory, ProjectDependencies(e.project))
        ComponentDialog(viewModel).show()
    }
}
