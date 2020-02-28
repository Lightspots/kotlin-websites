package ch.lightspots.it.web.pages

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState

class IndexComponent : RComponent<RProps, RState>(){

  override fun RBuilder.render() {
    +"Index"
  }
}