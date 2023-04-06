@file:JsModule("@monaco-editor/react")
@file:JsNonModule

import react.ComponentClass
import react.Props

@JsName("default")
external val Editor: ComponentClass<EditorProps>

external interface EditorProps : Props {
    var value: String
    var height: String
}

