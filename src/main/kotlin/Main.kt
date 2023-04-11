import csstype.HtmlAttributes
import csstype.HtmlAttributes.Companion.height
import csstype.HtmlAttributes.Companion.type
import js.core.asList
import js.promise.PromiseResult
import kotlinx.browser.window
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import mui.material.Button
import mui.material.ButtonVariant.Companion.contained
import mui.material.FormControlVariant.Companion.outlined
import mui.material.Stack
import mui.material.StackDirection.Companion.row
import mui.material.TextField
import mui.system.responsive
import react.FC
import react.Props
import react.ReactNode
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.h3
import web.dom.document
import web.html.HTML
import mui.material.Menu
import mui.material.MenuItem
import mui.material.MenuList
import mui.material.Alert
import mui.material.AlertTitle
import mui.material.Dialog
import mui.material.DialogActions
import mui.material.DialogContent
import mui.material.DialogTitle
import mui.material.DialogContentText
import mui.material.List
import mui.material.ListItemText
import mui.material.ListItem
import react.dom.events.MouseEventHandler
import react.dom.html.ReactHTML.dialog
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.textarea
import react.useState
import web.filesystem.FileSystemHandleKind.Companion.file
import react.dom.html.ReactHTML.input
import web.filesystem.FileSystemHandleKind.Companion.directory
import web.html.ImageDecoding.Companion.async
import web.html.InputType
import web.prompts.alert

fun main() {
    val root = document.createElement(HTML.div)
        .also { document.body.appendChild(it) }

    createRoot(root)
        .render(App.create())
}

val App = FC<Props> {
//    var isOpen by useState(false)
//
//    var inputRef by useState(null)
//
//    var isMenuFileOpen by useState(false)
//    var isMenuAboutOpen by useState(false)
    var editorValue by useState("")

    ReactHTML.div {


        Stack {
            NavBar {
                onFileLoad={ editorValue = it }
                editorText=editorValue
            }

            Editor {
                value = editorValue
                height = "63vh"
                onChange = {editorValue = it}
            }

            QueryEditor {}

            SolutionsContainer {}
            Footer{}

        }


//        Stack {
//            Button{
//                variant = contained
//                disabled = false
//                onClick = {
//                    isMenuFileOpen = true }
//                +"File"
//            }
//            Button{
//                variant = contained
//                disabled = false
//                onClick = {
//                    showAbout()
//                }
//                +"About"
//            }
//            Menu {
//                open = isMenuFileOpen
//
//                onClose = { isMenuFileOpen = false }
//                MenuItem {
//                    onClick = {
//                        isMenuFileOpen = false
//                        //funtestdiag2()
//                    }
//                    +"Open ..."
//                }
//
//                MenuItem {
//                    onClick = { isMenuFileOpen = false }
//                    +"Save As ..."
//                }
//                MenuItem {
//                    onClick = { isMenuFileOpen = false }
//                    +"Save"
//                }
//            }
//        }
//
//        input {
//            key = "zipCode"
//            type = InputType.file
//            name = "zipCode"
//            placeholder = "Enter ZIP code"
//            onChange = {
//                it.target.files?.get(0)?.text()?.then { it1 ->
//                    console.log(it1)
//                    editorValue = it1
//                }
//            }
//        }
//        div {
//            Button {
//                variant = contained
//                onClick = {
//                    console.log(inputRef)
//                }
//            }
//        }
//
//        div {
//            Button {
//                variant = contained
//                onClick = { isOpen = true }
//                +"Open dialog"
//            }
//
//            Dialog {
//                open = isOpen
//                onClose = { _, _ -> isOpen = false }
//
//                DialogTitle {
//                    +"About"
//                }
//                DialogContent {
//                    DialogContentText {
//                        +"TupKTWeb versione 0.1"
//                    }
//                    DialogContentText {
//
//                        h3 {
//                            +"basata su versione di Tuprolog"
//                        }
//                        h1 {
//                            +"dev by pollo111"
//                        }
//                    }
//                    DialogActions {
//                        Button {
//                            onClick = { isOpen = false }
//                            +"OK"
//                        }
//                        /*
//                        Button {
//                            onClick = { isOpen = false }
//                            +"Subscribe"
//                        }
//                         */
//                    }
//                }
//            }
//        }
    }

}


/* creare una promise o la fai lui e la svolte lui ???

private operator fun <T> Promise<T>?.get(t: T): Any {

}

 */


fun showAbout() {
   window.alert("SOSOSOSOOSOSOSSOSO")
}


/*    <SimpleDialog
    selectedValue={selectedValue}
    open={open}
    onClose={handleClose}
    />*/


//fun openFileFun() {
//
//
////    https://www.pluralsight.com/guides/uploading-files-with-reactjs
//
//
//}

//input {
//    key = "zipCode"
//
//    type = InputType.text
//    name = "zipCode"
//    value = zipCode
//    placeholder = "Enter ZIP code"
//    title = infoText
//    onChange = {
//        handleChange(it.target.value)
//    }
//}

//private data class Point(
//    val x: Double,
//    val y: Double,
//)




//        Tabs {
//            value = activeTab
//            onChange = { _, newValue -> activeTab = newValue }
//
//            Tab {
//                value = "one"
//                id = "0"
//                label = ReactNode("New Arrivals in the Longest Text of Nonfiction that should appear in the next line")
//                wrapped = true
//            }
//            Tab {
//                value = "two"
//                id = "1"
//                label = ReactNode("Item Two")
//            }
//            Tab {
//                value = "three"
//                id = "2"
//                label = ReactNode("Item Three")
//            }
//        }