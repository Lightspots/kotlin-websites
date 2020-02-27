package ch.lightspots.it.web

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.h1

class Application: RComponent<RProps, RState>() {
  override fun RBuilder.render() {
    h1 {
      +"Hello, React+Kotlin/JS"
    }
  }
}