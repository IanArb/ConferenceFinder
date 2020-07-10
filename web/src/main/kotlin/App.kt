import conferences.model.Conference
import conferences.utils.Result
import react.*
import react.dom.*
import kotlinx.coroutines.*
import kotlinx.html.style
import kotlinext.js.js
import kotlinx.html.DIV

val App = functionalComponent<RProps> {
    val appDependencies = useContext(AppDependenciesContext)
    val repository = appDependencies.repository

    val (conference, setConference) = useState(emptyList<Conference>())

    useEffectWithCleanup(dependencies = listOf()) {
        val mainScope = MainScope()

        mainScope.launch {
            val fetchConferences = repository.fetchConferences()
            if (fetchConferences is Result.Success) {
                val data = fetchConferences.data
                setConference(data)
            }
        }
        return@useEffectWithCleanup { mainScope.cancel() }
    }

    div {
        nav("navbar navbar-light bg-light") {
            span("navbar-brand mb-0 h1") {
                +"Conferences"
            }
        }
        buildContainer(conference)
    }

}

@Suppress("UnsafeCastFromDynamic")
private fun RDOMBuilder<DIV>.buildContainer(conference: List<Conference>) {
    div("container") {
        attrs {
            style = js {
                marginTop = "16px"
            }
        }
        buildRows(conference)
    }
}

private fun RDOMBuilder<DIV>.buildRows(conference: List<Conference>) {
    div("row justify-content-between") {
        conference.map { item ->
            buildColumns(item)
        }
    }
}

private fun RDOMBuilder<DIV>.buildColumns(item: Conference): ReactElement {
    return div("col-md-4") {
        buildCard(item)
    }
}

@Suppress("UnsafeCastFromDynamic")
private fun RDOMBuilder<DIV>.buildCard(item: Conference) {
    div("card") {
        attrs {
            style = js {
                width = "18rem"
                marginBottom = "24px"
            }
        }
        buildImage(item)

        buildCardBody(item)
    }
}

@Suppress("UnsafeCastFromDynamic")
private fun RDOMBuilder<DIV>.buildImage(item: Conference) {
    img("card-img-top") {
        attrs.src = item.logoUrl
        attrs {
            style = js {
                width = "286px"
                height = "180px"
            }
        }
    }
}

@Suppress("UnsafeCastFromDynamic")
private fun RDOMBuilder<DIV>.buildCardBody(item: Conference) {
    div("card-body") {
        h5("card-title") {
            +item.name
        }

        div {
            i("fas fa-location-arrow") {

            }
            p("card-text") {
                attrs {
                    style = js {
                        display = "inline-block"
                        marginLeft = "8px"
                    }
                }
                +"${item.location.country.name}, ${item.location.country.city}"
            }
        }
        div("card-text") {
            i("fas fa-calendar-day") {

            }
            p {
                attrs {
                    style = js {
                        display = "inline-block"
                        marginLeft = "8px"
                    }
                }
                +item.dates.startDate
            }
        }

        p("card-text") {
            attrs {
                style = js {
                    color = "green"
                }
            }
            +item.status
        }
    }
}