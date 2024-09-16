package ru.bmstr.demo.state

import com.intellij.openapi.components.Service
import com.intellij.openapi.components.SimplePersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project

@Service(Service.Level.PROJECT)
@State(name = "PersistentInspectionState", storages = [Storage("inspectionState.xml")])
class PersistentInspectionState : SimplePersistentStateComponent<InspectionState>(InspectionState()) {

    companion object {
        fun getInstance(project: Project): PersistentInspectionState =
            project.getService(PersistentInspectionState::class.java)
    }

}