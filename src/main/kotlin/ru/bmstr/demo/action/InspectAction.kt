package ru.bmstr.demo.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.PROJECT
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.progress.ProgressManager
import ru.bmstr.demo.task.BackgroundInspection

class InspectAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val vFile = VIRTUAL_FILE.getData(e.dataContext)!!
        val project = PROJECT.getData(e.dataContext)!!
        val task = BackgroundInspection(project, vFile)

        ProgressManager.getInstance().run(task)
    }
}
