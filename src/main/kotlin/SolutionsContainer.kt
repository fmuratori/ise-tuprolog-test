import mui.material.*
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML

import react.useState
val SolutionsContainer = FC<Props> {
    var activeTab by useState("one")

    Tabs {
        value = activeTab
        onChange = { _, newValue -> activeTab = newValue }

        Tab {
            value = "t1"
            label = ReactNode("Solutions")
            wrapped = true
        }
        Tab {
            value = "t2"
            label = ReactNode("Standard In")
        }
        Tab {
            value = "t3"
            label = ReactNode("Standard Out")
        }
    }
}