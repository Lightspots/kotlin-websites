package ch.lightspots.it.web

import ch.lightspots.it.react.router.dom.route
import ch.lightspots.it.web.pages.NotFoundComponent
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.router.dom.hashRouter
import react.router.dom.route
import react.router.dom.switch

class Website : RComponent<RProps, RState>() {
  override fun RBuilder.render() {
    div("section") {
      div("container") {
        header()
        hashRouter {
          switch {
            Link.values().forEach {
              route(it.route, it.component, exact = true)
            }
            route(component = NotFoundComponent::class)
          }
          footer()
        }
      }
    }
  }
}