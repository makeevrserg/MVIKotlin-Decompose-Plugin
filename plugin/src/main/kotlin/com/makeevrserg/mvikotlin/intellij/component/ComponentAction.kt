package com.makeevrserg.mvikotlin.intellij.component

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.psi.PsiDirectory
import com.makeevrserg.mvikotlin.intellij.component.feature.ComponentFeature
import com.makeevrserg.mvikotlin.intellij.component.ui.ComponentDialog
import com.makeevrserg.mvikotlin.intellij.data.impl.StorageApiImpl
import com.makeevrserg.mvikotlin.intellij.dependencies.ProjectDependencies

class ComponentAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val directory = e.getData(CommonDataKeys.PSI_ELEMENT) as? PsiDirectory ?: return
        val properties: PropertiesComponent = PropertiesComponent.getInstance(project)
        val storageApi = StorageApiImpl(properties)
        val viewModel = ComponentFeature(storageApi, directory, ProjectDependencies(project))
        ComponentDialog(viewModel).show()
    }
}
