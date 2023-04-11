import mui.material.*
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.dom.html.ReactHTML

val QueryEditor = FC<Props> {
    Stack {
        direction = responsive(StackDirection.row)
        TextField {
            id = "query"
            label = ReactNode("Query")
            variant = FormControlVariant.outlined
            fullWidth = true
        }
        Button {
            variant = ButtonVariant.contained
            +"Solve"
        }
        Button {
            variant = ButtonVariant.contained
            +"Solve All"
        }
        Button {
            variant = ButtonVariant.contained
            disabled = true
            +"Stop"
        }
        Button {
            variant = ButtonVariant.contained
            +"Reset"
        }
    }
}