
import mui.material.*
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.useState


external interface QueryEditorProps : Props {
    var onSolve: (String) -> Unit
    var onSolveAll: () -> Unit
    var onStop: () -> Unit
    var onReset: () -> Unit
    var takeTextFromEditor: () -> String
}

class Query (val queryToSolve: String){

}

val QueryEditor = FC<QueryEditorProps> {
    props ->
    var myQuery by useState("myTestQuery")

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
            onClick = { props.onSolve(myQuery) }

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