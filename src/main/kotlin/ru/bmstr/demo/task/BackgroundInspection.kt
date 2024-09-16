package ru.bmstr.demo.task

import com.intellij.openapi.application.ModalityState
import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import ru.bmstr.demo.state.PersistentInspectionState

class BackgroundInspection(
    project: Project,
    val vFile: VirtualFile
) : Task.Backgroundable(project, "Inspection of the file") {

    override fun run(indicator: ProgressIndicator) {
        indicator.isIndeterminate = false
        indicator.fraction = 0.0
        while (indicator.fraction < 1) {
            indicator.fraction += 0.1
            Thread.sleep(100)
        }

        invokeLater(ModalityState.defaultModalityState()) {
            runWriteAction {
                val inspectionVfile = vFile.parent.findOrCreateChildData(this, "${vFile.name}-inspection.txt")
                inspectionVfile.setBinaryContent("""
                    Inspection successful!
                    Results: ...
                """.trimIndent().toByteArray())
                PersistentInspectionState.getInstance(project).state.count += 1
            }
        }

    }
}