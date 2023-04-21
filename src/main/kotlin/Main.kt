import js.uri.encodeURIComponent
import mui.lab.TabContext
import mui.lab.TabPanel
import mui.material.*
import react.*
import react.dom.client.createRoot
import react.dom.html.ReactHTML
import react.redux.Provider
import react.redux.useDispatch
import react.redux.useSelector
import redux.RAction
import redux.compose
import redux.createStore
import redux.rEnhancer
import web.dom.document
import web.html.HTML
import kotlin.js.Date
import kotlin.reflect.KProperty1

// Utils
fun <S, A, R> combineReducers(reducers: Map<KProperty1<S, R>, Reducer<*, A>>): Reducer<S, A> {
    return redux.combineReducers(reducers.mapKeys { it.key.name })
}

// Stato
data class Counter(var count: Int)

data class State(
    val counter: Counter = Counter(10)
)

// Azioni disponibili

class Increase : RAction

class Decrease : RAction

// Gestione di azioni
fun counter(counter: Counter = Counter(10), action: RAction): Counter = when (action) {
    is Increase -> {
        counter.count++
        counter
    }

    is Decrease -> {
        counter.count--
        counter
    }

    else -> counter
}
// Redux Store

fun rootReducer(
    state: State,
    action: Any
) = State(
    counter(state.counter, action.unsafeCast<RAction>()),
)

val myStore = createStore(
    ::rootReducer,
    State(),
    rEnhancer()
)

fun main() {
    val root = document.createElement(HTML.div)
        .also { document.body.appendChild(it) }

    createRoot(root)
        .render(App.create())
}

class EditorTab(var fileName: String, var editorValue: String)

val App = FC<Props> {
    Provider {
        store = myStore

        Root{}
    }
}

private val Root = FC<Props> {

        var editorSelectedTab by useState("")
        val editorTabs by useState(mutableListOf<EditorTab>())

        var isErrorAlertOpen by useState(false)
        var errorAlertMessage by useState("")


        fun addNewEditor() {
            val fileName: String = "undefined_" + Date().getTime() + ".pl"
            editorTabs.add(
                EditorTab(
                    fileName, """
            % member2(List, Elem, ListWithoutElem)
            member2([X|Xs],X,Xs).
            member2([X|Xs],E,[X|Ys]):-member2(Xs,E,Ys).
            % permutation(Ilist, Olist)
            permutation([],[]).
            permutation(Xs, [X | Ys]) :-
            member2(Xs,X,Zs),
            permutation(Zs, Ys).

            % permutation([10,20,30],L).
        """.trimIndent()
                )
            )
            editorSelectedTab = fileName
        }

        useEffectOnce {
            addNewEditor()
        }

        ReactHTML.div {
            Stack {

                NavBar {
                    onFileLoad = { fileName: String, editorValue: String ->
                        if (editorTabs.find { it.fileName == fileName } == null) {
                            editorTabs.add(EditorTab(fileName, editorValue))
                        } else {
                            errorAlertMessage = "File already exists"
                            isErrorAlertOpen = true
                        }
                        editorSelectedTab = fileName
                    }
                    onAddEditor = {
                        addNewEditor()
                    }
                    onCloseEditor = {
                        if (editorTabs.size > 1) {
                            // find the deletable tab panel index
                            val index = editorTabs.indexOfFirst { it.fileName == editorSelectedTab }
                            editorTabs.removeAt(index)
                            // select new ide
                            if (index == 0)
                                editorSelectedTab = editorTabs[index].fileName
                            else
                                editorSelectedTab = editorTabs[index - 1].fileName
                        }
                    }
                    onDownloadTheory = {
                        val editorText =
                            editorTabs.find { it2 -> it2.fileName == editorSelectedTab }?.editorValue ?: ""
                        if (editorText != "") {
                            val elem = document.createElement(HTML.a)
                            elem.setAttribute(
                                "href",
                                "data:text/plain;charset=utf-8," + encodeURIComponent(editorText)
                            )
                            elem.setAttribute("download", editorSelectedTab)
                            elem.click()
                            isErrorAlertOpen = false
                        } else {
                            errorAlertMessage = "No theory specified"
                            isErrorAlertOpen = true
                        }
                    }

                    currentFileName = editorSelectedTab

                    onRenameEditor = { it ->
                        val isOk: EditorTab? = editorTabs.find { it3 -> it3.fileName == it }
                        if (isOk == null) {
                            val indexForRename =
                                editorTabs.indexOfFirst { it3 -> it3.fileName == editorSelectedTab }
                            editorTabs[indexForRename].fileName = it
                            editorSelectedTab = editorTabs[indexForRename].fileName
                            isErrorAlertOpen = false
                        } else {
                            errorAlertMessage = if (it != editorSelectedTab)
                                "Cannot rename file. A file with this name already exists"
                            else
                                "Cannot rename file with the same value"
                            isErrorAlertOpen = true
                        }
                    }

                }

                TabContext {
                    value = editorSelectedTab
                    Tabs {
                        value = editorSelectedTab
                        variant = TabsVariant.scrollable
                        scrollButtons = TabsScrollButtons.auto
                        onChange = { _, newValue ->
                            editorSelectedTab = newValue as String
                        }

                        editorTabs.forEach {
                            Tab {
                                value = it.fileName
                                label = ReactNode(it.fileName)
                                wrapped = true
                            }
                        }
                    }

                    editorTabs.forEach {
                        TabPanel {
                            value = it.fileName
                            Editor {
                                value = it.editorValue
                                height = "63vh"
                                onChange = {
                                    editorTabs.find { it2 -> it2.fileName == editorSelectedTab }?.editorValue = it
                                }
                                beforeMount = {
                                    console.log(it)
                                }
                            }
                        }
                    }
                }

                Snackbar {
                    open = isErrorAlertOpen
                    autoHideDuration = 6000
                    onClose = { _, _ -> isErrorAlertOpen = false }

                    Alert {
                        severity = AlertColor.error
                        +errorAlertMessage
                    }
                    // TODO: change snack-bar anchor
                }

                QueryEditor {}

                SolutionsContainer {}

                Footer {}
            }
        }
}


//val App = FC<Props> {


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
//    }
//
//}


/* creare una promise o la fai lui e la svolte lui ???

private operator fun <T> Promise<T>?.get(t: T): Any {

}

 */


//fun showAbout() {
//    window.alert("SOSOSOSOOSOSOSSOSO")
//}


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