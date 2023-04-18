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
import kotlin.math.exp
import kotlin.math.pow
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit
import kotlin.time.toDuration


val Footer = FC<Props> {
    var state by useState(0)
    var timeoutDuration by useState(10)

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
                                + "Timeout: ${timeoutDuration.toDuration(DurationUnit.MILLISECONDS)}"
                            }
                        }

                        Grid {
                            item = true
                            xs = 7
                            Slider {
                                value = timeoutDuration
                                step = 10
                                onChange = { _, newValue, _ -> timeoutDuration = newValue as Int }
                                max = 1000
                                min = 1
                            }
                        }
                    }
            }
        }
    }
}