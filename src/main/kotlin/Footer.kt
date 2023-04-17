import csstype.AlignItems
import csstype.JustifyContent
import csstype.PropertyName
import csstype.PropertyName.Companion.justifyContent
import csstype.em
import emotion.react.css
import mui.material.*
import mui.system.Breakpoint.Companion.xs
import mui.system.ResponsiveStyleValue
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.ReactNode
import react.dom.aria.ariaLabel
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.p

import react.useState
import utils.xs

val Footer = FC<Props> {
    var state by useState(0)
    var timeoutDuration by useState(5)

    BottomNavigation {
        showLabels = true
        value = state
        onChange = { _, value -> state = value }

        Grid {
            container = true

            direction = responsive(GridDirection.row)
            sx {
                justifyContent = JustifyContent.spaceBetween
                alignItems = AlignItems.center
                padding = 2.em;
            }

                Grid {
                    item = true
                    p {
                        + "Stato: IDLE"
                    }
                }
                Grid {
                    item = true
                    xs = 5

                    Grid {
                        container = true
                        direction = responsive(GridDirection.row)

                        Grid {
                            item = true
                            xs = 5
                            Typography {
                                +("Timeout: $timeoutDuration ms")
                            }
                        }

                        Grid {
                            item = true
                            xs = 7
                            Slider {
                                value = timeoutDuration
                                onChange = { _, newValue, _ -> timeoutDuration = newValue }
                                max = 360000
                                min = 10
                            }
                        }
                    }

            }

        }
    }
}