import mui.material.*
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.dom.aria.ariaLabel
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.p

import react.useState
val Footer = FC<Props> {
    var state by useState(0)
    var timeoutDuration by useState(5)

    BottomNavigation {
        showLabels = true
        value = state
        onChange = { _, value -> state = value }

        Stack {
            direction = responsive(StackDirection.row)

            p {
                + "Stato: IDLE"
            }

            Slider {
                ariaLabel = "Volume"
                value = timeoutDuration
                onChange = { _, newValue, _ -> timeoutDuration = newValue }
            }

        }
    }
}