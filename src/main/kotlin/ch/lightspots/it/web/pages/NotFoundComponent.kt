package ch.lightspots.it.web.pages

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class NotFoundComponent : RComponent<RProps, RState>(){

  override fun RBuilder.render() {
    +"404 Not Found"
  }
}