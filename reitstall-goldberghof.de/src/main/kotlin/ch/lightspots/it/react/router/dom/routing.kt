package ch.lightspots.it.react.router.dom

import react.Component
import react.RBuilder
import react.RClass
import react.RProps
import react.ReactElement
import kotlin.reflect.KClass

fun RBuilder.route(
    path: String? = null,
    component: KClass<out Component<*, *>>,
    exact: Boolean = false,
    strict: Boolean = false
): ReactElement {
  return child<RouteProps<RProps>, RouteComponent<RProps>> {
    attrs {
      this.path = path
      this.exact = exact
      this.strict = strict
      this.component = component.js.unsafeCast<RClass<RProps>>()
    }
  }
}

