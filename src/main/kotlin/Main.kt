
import mui.lab.TabPanel
import react.*
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML
import web.dom.document
import mui.material.Button
import mui.material.ButtonVariant.Companion.contained
import mui.material.TextField
import react.dom.html.ReactHTML.h3
import web.html.HTML
import react.ReactNode
import mui.material.FormControlVariant.Companion.standard
import mui.material.Tab
import mui.material.Tabs

fun main() {
    val root = document.createElement(HTML.div)
        .also { document.body.appendChild(it) }

    createRoot(root)
        .render(App.create())
}

val App = FC<Props> {

    var activeTab by useState("one")


    ReactHTML.div {

        TestEditor {}


        h3 {
            +"IDE tuProlog"
        }

        Button {
            variant = contained
            +"Text"
        }

        TextField {
            id = "standard-basic"
            label = ReactNode("Standard")
            variant = standard
        }

        Tabs {
            value = activeTab
            onChange = { _, newValue -> activeTab = newValue }

            Tab {
                value = "one"
                id = "0"
                label = ReactNode("New Arrivals in the Longest Text of Nonfiction that should appear in the next line")
                wrapped = true
            }
            Tab {
                value = "two"
                id = "1"
                label = ReactNode("Item Two")
            }
            Tab {
                value = "three"
                id = "2"
                label = ReactNode("Item Three")
            }
        }


//        TabPanel {
//            value = activeTab
//            index = 0
//        }
//        TabPanel {
//            value = activeTab
//            index = "1"
//        }
//        TabPanel {
//            value = activeTab
//            index = "2"
//        }
    }
}
