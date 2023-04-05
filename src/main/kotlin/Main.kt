
import react.*
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML
import web.dom.document
import mui.material.Button
import mui.material.ButtonVariant.Companion.contained
import react.dom.html.ReactHTML.h3
import web.html.HTML

fun main() {
    val root = document.createElement(HTML.div)
        .also { document.body.appendChild(it) }

    createRoot(root)
        .render(App.create())
}

val App = FC<Props> {

    ReactHTML.div {
        h3 {
            +"ASD"
        }

        Button {
            variant = contained
            +"Text"
        }

    }
}
