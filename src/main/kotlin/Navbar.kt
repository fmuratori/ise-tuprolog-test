import csstype.Color
import csstype.PropertyName.Companion.maxHeight
import csstype.PropertyName.Companion.padding
import csstype.PropertyName.Companion.paddingRight
import csstype.TextShadow
import csstype.em
import csstype.px
import emotion.react.css
import mui.material.*
import mui.material.ButtonVariant.Companion.contained
import mui.material.styles.TypographyVariant
import mui.system.responsive
import react.FC
import react.Props
import react.createRef
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.link
import react.useState
import web.html.HTMLInputElement
import web.html.InputType

external interface NavBarProps : Props {
    var onFileLoad: (String, String) -> Unit
    var onCloseEditor: () -> Unit
    var onAddEditor: () -> Unit
    var onDownloadTheory: () -> Unit
}

val NavBar = FC<NavBarProps> { props ->
    var isDialogOpen by useState(false)
    val inputRef = createRef<HTMLInputElement>()

    Stack {
        direction = responsive(StackDirection.row)

        img {
            src = "https://raw.githubusercontent.com/tuProlog/2p-kt/master/.img/logo.svg"
            height = 56.0
            width = 56.0
            css {
                paddingRight = 1.em
            }
        }

        Typography {
            variant=TypographyVariant.h4
            gutterBottom = true

            +"IDE web"
            css {
                color= Color("blue")
                textShadow=TextShadow(3.px, 3.px, 3.px, Color("red"))
            }
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


//        SvgIcon {
//            fontSize=SvgIconSize.large
//            path = {
//                d="https://raw.githubusercontent.com/tuProlog/2p-kt/master/.img/logo.svg"
//            }
//        }

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
    }
}