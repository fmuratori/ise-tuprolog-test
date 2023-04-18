
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
import react.dom.onChange
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

                }

                TextField {
                    ref =
                    //console.log(it.target.value)

                   // value =  {
                  //      this.value.name
                  //  }
                    //inputRef =
                    // = { _, _ -> isDialogOpen = false }
                    //asDynamic().InputProps = value
                    //value = "pollo4.po" +
                             //nameToChange
                    //value =
                    //value = onChange.toString()
                    //value = nameToChange
                    id="new_name_editor"
                    //value = onChange.asDynamic()
                    /*input {
                        console.log(value)
                        //console.log(target.
                    }*/
                   // value =
                    //InputProps = inputProps.
                    //asDynamic().InputProps = value

                    //console.log(asDynamic().InputProps)
                    //console.log(value)
                    /*
                    InputProps = jso {
                        startAdornment = InputAdornment.create {
                            position = InputAdornmentPosition.start
                            +"kg"
                        }
                        console.log(value)
                    }*/

                   // label= ReactNode("New name")
                    type= InputType.text
                    variant= FormControlVariant.outlined
                    onChange = {
                      //  nameToChange = it.target.na
                        //console.log(onChange.)
                       // it.target.dispatchEvent(onChange.a)
                        //TextField. = onChange

                        //console.log(it.target.asDynamic())
                        //console.log(onChange.asDynamic().toString())
                        //console.log(InputProps.inputProps.set())
                        //console.log(it.target.dispatchEvent(onChange.asDynamic())
                       // console.log(form)
                        console.log(TextField)
                        console.log("1+"+value)
                        console.log("2+"+this.value)
                       // console.log("3+" + value.name)
                        //console.log(value.asDynamic(name))
                       // console.log("4"+this.value.name)
                        console.log("5"+TextField.displayName)
                        console.log("6"+ TextField)
                      //  console.log("7"+ MuiTextField)

                       // console.log(this.inputProps)
                       // console.log(this.itemID)
                        //console.log(this.value)
                      //  console.log(ariaValueText)
                       // console.log(this.value)
                       // console.log()
                       // console.log(input)
                        //input
                    //    console.log(inputProps)
                        //
                        //nameToChange = it.target.toString()
                    }
                    //console.log(it.target)
                    autoFocus
                    fullWidth
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
                            console.log(nameToChange)
                            isDialogRenameOpen = false }
                        +"Confirm"
                    }
                }
            }
        }
    }
}