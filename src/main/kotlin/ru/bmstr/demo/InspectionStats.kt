package ru.bmstr.demo

import com.intellij.icons.AllIcons
import com.intellij.openapi.application.invokeLater
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.components.JBLabel
import com.intellij.ui.content.ContentFactory
import ru.bmstr.demo.state.PersistentInspectionState
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JPanel
import javax.swing.SwingUtilities

class InspectionStats : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        val panel = createPanel(project)
        val content = ContentFactory.getInstance().createContent(panel, "Inspection Statistics", false)
        toolWindow.contentManager.addContent(content)
    }

    private fun createPanel(project: Project): JPanel {
        val panel = JPanel()
        invokeLater {
            panel.layout = BorderLayout()
            val inspections = PersistentInspectionState.getInstance(project).state
            val label = JBLabel("Total inspection count: ${inspections.count}")
            panel.add(label, BorderLayout.CENTER)

            val btn = JButton("Refresh", AllIcons.Actions.Refresh).apply {
                addActionListener(object : ActionListener {
                    override fun actionPerformed(e: ActionEvent?) {
                        val inspections = PersistentInspectionState.getInstance(project).state
                        label.text = "Total inspection count: ${inspections.count}"
                    }
                })
            }
            panel.add(btn, BorderLayout.NORTH)
        }
        return panel
    }
}
