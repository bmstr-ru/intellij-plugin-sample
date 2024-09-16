package ru.bmstr.demo.state

import com.intellij.openapi.components.BaseState

class InspectionState : BaseState() {
    var count by property(0)
}