package com.makeevrserg.mvikotlin.intellij.dependencies

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.Application
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.makeevrserg.mvikotlin.intellij.core.TemplateGenerator

/**
 * Author
 *
 * @see <a href="https://github.com/levinzonr/jetpack-compose-ui-arch-plugin">levinzonr/jetpack-compose-ui-arch-plugin</a>
 */
@Suppress("MaxLineLength")
class ProjectDependencies(project: Project) {
    val generator = TemplateGenerator(project)
    val editor: FileEditorManager = FileEditorManager.getInstance(project)
    val properties: PropertiesComponent = PropertiesComponent.getInstance(project)
    val application: Application = ApplicationManager.getApplication()
}
