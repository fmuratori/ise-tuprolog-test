
import csstype.AlignItems.Companion.center
import csstype.Color
import csstype.JustifyContent.Companion.spaceBetween
import csstype.TextShadow
import csstype.em
import csstype.px
import emotion.react.css
import mui.material.*
import mui.material.ButtonVariant.Companion.contained
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.FC
import react.Props
import react.createRef
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.input
import react.useState
import web.html.HTMLInputElement
import web.html.InputType

external interface NavBarProps : Props {
    var onFileLoad: (String, String) -> Unit
    var onCloseEditor: () -> Unit
    var onAddEditor: () -> Unit
    var onDownloadTheory: () -> Unit
    var onRenameEditor: (String) -> Unit
}

val NavBar = FC<NavBarProps> { props ->
    var isDialogOpen by useState(false)
    var isDialogRenameOpen by useState(false)
    var inputRef = createRef<HTMLInputElement>()
    var nameToChange by useState("")
    var eventDialog by useState(null)
   // var var1 by useRef(null)

    Stack {
        direction = responsive(StackDirection.row)
        spacing = responsive(5)

        sx {
            justifyContent = spaceBetween
            alignItems = center
        }

        div {
            img {
                src = "https://raw.githubusercontent.com/tuProlog/2p-kt/master/.img/logo.svg"
                height = 56.0
                width = 56.0
                css {
                    paddingRight = 1.em
                    paddingLeft = 2.em
                }
            }
            Typography {
                variant = TypographyVariant.h4
                gutterBottom = true
                +"IDE web"
                css {
                    color = Color("blue")
                    textShadow = TextShadow(3.px, 3.px, 3.px, Color("red"))
                }
            }
        }

        div {
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
                    props.onDownloadTheory()
                }
                +"Download"
            }
            Button {
                variant = contained
                onClick = { isDialogOpen = true }
                +"About"
            }
            Button {
                variant = contained
                onClick = { props.onAddEditor() }
                +"Add editor"
            }
            Button {
                variant = contained
                onClick = { props.onCloseEditor() }
                +"Remove editor"
            }
            Button {
                variant = contained
                onClick = { isDialogRenameOpen = true }
                +"Rename editor"
            }
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
                DialogActions {
                    Button {
                        onClick = { isDialogOpen = false }
                        +"Close"
                    }
                }
            }
        }

        Dialog {
            open = isDialogRenameOpen
            onClose = { _, _ -> isDialogRenameOpen = false }

            DialogTitle {
                +"Rename editor"
            }
            DialogContent {
                DialogContentText {
                    +"Change the name of the editor"
                }

                input {
                    type = InputType.text
                    ref = inputRef
                    hidden = false
                    onChange = {
                        console.log(it.target.value)
                        nameToChange = it.target.value

                    }
                    autoFocus
                }

                DialogActions {
                    Button {
                        onClick = {
                            isDialogRenameOpen = false }
                        +"Cancel"
                    }
                    Button {
                        onClick = {
                            props.onRenameEditor(nameToChange)
                            console.log("new name is " + nameToChange)
                            isDialogRenameOpen = false }
                        +"Confirm"
                    }
                }
            }
        }
    }
}