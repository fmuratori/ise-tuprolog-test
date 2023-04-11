import js.uri.encodeURIComponent
import mui.material.*
import mui.material.ButtonVariant.Companion.contained
import mui.system.responsive
import org.w3c.dom.url.URL
import react.FC
import react.Props
import react.PropsWithChildren
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.h3
import react.dom.html.ReactHTML.input
import react.useState
import web.dom.document
import web.html.HTML
import web.html.HTML.h3
import web.html.InputType

//import kotlinx.browser.window
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag
import web.html.HTMLAnchorElement
import web.window.window

external interface NavBarProps : Props {
    var onFileLoad: (String) -> Unit
    var editorText: String
}

val NavBar = FC<NavBarProps> { props ->
    var isOpen by useState(false)


    Stack {
        direction = responsive(StackDirection.row)

        ReactHTML.h3 {
            +"IDE tuProlog web"
        }

        input {
            type = InputType.file
            onChange = {
                it.target.files?.get(0)?.text()?.then { it1 ->
                    props.onFileLoad(it1)
                }
            }
        }

//        Button {
//            input {
//                type = InputType.file
//                hidden = true
//                onChange = {
//                    it.target.files?.get(0)?.text()?.then { it1 ->
//                        props.onFileLoad(it1)
//                    }
//                }
//            }
//        }

//        Button {
//            variant = contained
//            +"Upload"
//
//            input {
//                hidden = true
//                type = InputType.file
//                onChange = {
//                    console.log("ASD1")
//                }
//                onClick = {
//                    console.log("ASD1")
//                }
//            }
//            onClick = {
//                console.log("ASD2")
//            }
//
//        }

        Button {
            variant = contained
            onClick = { isOpen = true }
            +"Open dialog"
        }
        Dialog {
                open = isOpen
                onClose = { _, _ -> isOpen = false }

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
                            onClick = { isOpen = false }
                            +"OK"
                        }
                    }
                }
            }


        Button {
            variant = contained
            onClick = {
                var elem = document.createElement(HTML.a)
                elem.setAttribute("href",  "data:text/plain;charset=utf-8," + encodeURIComponent(props.editorText))
                elem.setAttribute("download",  "theory.pl")
                elem.click()
                console.log(props.editorText)
            }
            +"Download"
        }
    }
}