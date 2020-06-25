import conferences.repository.ConferencesRepository
import react.child
import react.createContext
import react.dom.render
import kotlin.browser.document


object AppDependencies {
    val repository = ConferencesRepository()
}

val AppDependenciesContext = createContext<AppDependencies>()


fun main() {
    render(document.getElementById("root")) {
        AppDependenciesContext.Provider(AppDependencies) {
            child(functionalComponent = App)
        }
    }
}