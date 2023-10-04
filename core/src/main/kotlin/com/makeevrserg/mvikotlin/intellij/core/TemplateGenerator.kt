package com.makeevrserg.mvikotlin.intellij.core

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.psi.PsiDirectory
import com.intellij.psi.PsiFile
import java.util.*

/**
 * Author
 *
 * @see <a href="https://github.com/levinzonr/jetpack-compose-ui-arch-plugin">levinzonr/jetpack-compose-ui-arch-plugin</a>
 */
class TemplateGenerator(private val project: Project) {

    fun generateKt(
        templateName: String,
        fileName: String,
        directory: PsiDirectory,
        properties: MutableMap<String, Any>
    ): PsiFile {
        val existing = directory.findFile("$fileName.kt")
        if (existing != null) return existing

        val manager = FileTemplateManager.getInstance(project)
        val template = manager.getInternalTemplate(templateName)
        properties[PropertyKeys.PackageName] = requireNotNull(directory.getPackageName())
        return FileTemplateUtil.createFromTemplate(
            template,
            "$fileName.kt",
            properties.toProperties(),
            directory
        ) as PsiFile
    }

    private fun PsiDirectory.getPackageName(): String? {
        return ProjectRootManager.getInstance(project)
            .fileIndex
            .getPackageNameByDirectory(virtualFile)
    }

    private fun Map<String, Any>.toProperties(): Properties {
        return Properties().apply {
            this@toProperties.forEach { setProperty(it.key, it.value.toString()) }
        }
    }
}
