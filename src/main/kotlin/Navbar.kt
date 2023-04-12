import js.core.Object
import js.uri.encodeURIComponent
import mui.material.*
import mui.material.ButtonVariant.Companion.contained
import mui.material.Orientation.Companion.horizontal
import mui.material.Orientation.Companion.vertical
import mui.system.responsive
import react.*
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import web.dom.document
import web.html.HTML
import web.html.HTMLInputElement
import web.html.InputType
external interface NavBarProps : Props {
    var onFileLoad: (String, String) -> Unit
    var editorText: String
}

val NavBar = FC<NavBarProps> { props ->
    var isDialogOpen by useState(false)
    var isDownloadErrorAlertOpen by useState(false)
    val inputRef = createRef<HTMLInputElement>()

    Stack {
        direction = responsive(StackDirection.row)

        ReactHTML.h3 {
            +"IDE tuProlog web"
        }

        input {
            type = InputType.file
            ref = inputRef
            hidden = true
            onChange = {
                it.target.files?.get(0)?.text()?.then { it1 ->
                    props.onFileLoad(it.target.files?.get(0)?.name ?: "ERROR", it1)
                    it.target.value = ""
                }
            }
        }
        Button {
            variant = contained
            onClick = { inputRef.current?.click() }
            +"Upload"
        }
        Button {
            variant = contained
            onClick = {
                isDownloadErrorAlertOpen = if (props.editorText.isNotBlank()) {
                    val elem = document.createElement(HTML.a)
                    elem.setAttribute("href", "data:text/plain;charset=utf-8," + encodeURIComponent(props.editorText))
                    elem.setAttribute("download", "theory.pl")
                    elem.click()
                    false
                } else {
                    true
                }
            }
            +"Download"
        }
        Snackbar {
            open = isDownloadErrorAlertOpen
            autoHideDuration = 6000
            onClose = {_, _ -> isDownloadErrorAlertOpen=false}

            Alert {
              severity = AlertColor.error
                + "No theory specified"
            }
            // TODO: change snack-bar anchor
        }
        Button {
            variant = contained
            onClick = { isDialogOpen = true }
            +"About"
        }
        Dialog {
            open = isDialogOpen
            onClose = { _, _ -> isDialogOpen = false }

            DialogTitle {
                +"About"
            }
            DialogContent {
                DialogContentText {
                    +"TupKTWeb versione 0.1"
                }
                DialogContentText {

                    h3 {
                        +"basata su versione di Tuprolog"
                    }
                    h1 {
                        +"dev by pollo111"
                    }
                }
                DialogActions {
                    Button {
                        onClick = { isDialogOpen = false }
                        +"OK"
                    }
                }
            }
        }
    }
}