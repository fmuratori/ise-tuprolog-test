
//import csstype.PropertyName.Companion.paddingRight
import csstype.AlignItems.Companion.center
import csstype.Color
import csstype.JustifyContent.Companion.spaceBetween
import csstype.TextShadow
import csstype.em
import csstype.px
import emotion.react.css
import mui.icons.material.*
import mui.material.*
import mui.material.ButtonVariant.Companion.contained
import mui.material.ButtonVariant.Companion.outlined
import mui.material.styles.TypographyVariant
import mui.system.responsive
import mui.system.sx
import react.*
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.img
import react.dom.html.ReactHTML.input
import react.dom.onChange
import web.html.HTMLInputElement
import web.html.InputType

external interface NavBarProps : Props {
    var onFileLoad: (String, String) -> Unit
    var onCloseEditor: () -> Unit
    var onAddEditor: () -> Unit
    var onDownloadTheory: () -> Unit
    var onRenameEditor: (String) -> Unit
    var currentFileName: String
}

//TODO far visualizzare le tab con il nome non tutto maiuscolo
val NavBar = FC<NavBarProps> { props ->
    var isDialogOpen by useState(false)
    var isDialogRenameOpen by useState(false)
    var newFileName by useState(props.currentFileName)
    val uploadInputRef = createRef<HTMLInputElement>()
    val inputRef2 = createRef<HTMLInputElement>()
    var changeFileNameErrorInput by useState(false)

    Stack {
        direction = responsive(StackDirection.row)
        spacing = responsive(10)

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

            Button {
                startIcon = AddCircleOutline.create()
                variant = contained
                onClick = { props.onAddEditor() }
                Typography {
                    +"Add"
                }
                sx {
                    marginRight = 1.em
                    marginLeft = 1.em
                }
            }
            input {
                type = InputType.file
                ref = uploadInputRef
                hidden = true
                accept = ".pl, .txt"
                multiple = false
                onChange = {
                    it.target.files?.get(0)?.text()?.then { it1 ->
                        props.onFileLoad(it.target.files?.get(0)?.name ?: "ERROR", it1)
                        it.target.value = ""
                    }
                }
            }
            Button {
                startIcon = UploadFileOutlined.create()
                variant = outlined
                onClick = { uploadInputRef.current?.click() }
                Typography {
                    +"Upload"
                }
                sx {
                    marginRight = 1.em
                    marginLeft = 1.em
                }
            }
            Button {
                startIcon = DownloadForOfflineOutlined.create()
                variant = outlined
                onClick = {
                    props.onDownloadTheory()
                }
                Typography {
                    +"Download"
                }
                sx {
                    marginRight = 1.em
                    marginLeft = 1.em
                }
            }
            Button {
                startIcon = DeleteForever.create()
                variant = outlined
                onClick = { props.onCloseEditor() }
                Typography {
                    +"Remove"
                }
                sx {
                    marginRight = 1.em
                    marginLeft = 1.em
                }
            }
            Button {
                startIcon = DriveFileRenameOutline.create()
                variant = outlined
                onClick = {
                    newFileName = props.currentFileName
                    changeFileNameErrorInput = false
                    isDialogRenameOpen = true
                }
                Typography {
                    +"Rename"
                }
                sx {
                    marginRight = 1.em
                    marginLeft = 1.em
                }
            }
            Button {
                startIcon = Info.create()
                variant = contained
                onClick = { isDialogOpen = true }
                Typography {
                    +"About"
                }
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
                    +"Write here the new name of ${props.currentFileName}"
                }
                TextField {
                    autoFocus = true
                    inputRef = inputRef2
                    fullWidth = true
                    error = changeFileNameErrorInput
                    label = ReactNode("New file name")
                    helperText = ReactNode("File name must end with .pl or .txt")
                    defaultValue = props.currentFileName
                    onChange = {
                        inputRef2.current?.let { it1 ->
                            newFileName = it1.value
                            changeFileNameErrorInput = !(it1.value.matches(Regex("\\w+(\\.pl|\\.txt)\$")))
                        }
                    }
                }
            }
            DialogActions {
                Button {
                    onClick = {
                        isDialogRenameOpen = false
                    }
                    +"Cancel"
                }
                Button {
                    disabled = changeFileNameErrorInput
                    onClick = {
                        isDialogRenameOpen = false
                        props.onRenameEditor(newFileName)
                    }
                    +"Confirm"
                }
            }
        }
    }
}