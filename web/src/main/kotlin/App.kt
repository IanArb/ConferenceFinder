import conferences.model.Conference
import conferences.utils.Result
import react.*
import react.dom.*
import kotlinx.coroutines.*
import kotlinx.html.style
import kotlinext.js.js

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
        div("container") {
            attrs {
                style = js {
                    marginTop = "16px"
                }
            }
            div("row justify-content-between") {
                conference.map { item ->
                    div("col-md-4") {

                        div("card") {
                            attrs {
                                style = js {
                                    width = "18rem"
                                    marginBottom = "24px"
                                }
                            }
                            img("card-img-top") {
                                attrs.src = item.logoUrl
                                attrs {
                                    style = js {
                                        width = "286px"
                                        height = "180px"
                                    }
                                }
                            }

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
                    }
                }
            }
        }


    }

}