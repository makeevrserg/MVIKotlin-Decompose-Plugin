package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.data.impl.StorageApiImpl
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies

class ComponentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as PsiDirectory
        val properties: PropertiesComponent = PropertiesComponent.getInstance(e.project!!)
        val storageApi = StorageApiImpl(properties)
        val viewModel = ComponentViewModel(storageApi, directory, ProjectDependencies(e.project))
        ComponentDialog(viewModel).show()
    }
}
