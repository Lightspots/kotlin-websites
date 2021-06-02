package ch.lightspots.it.react.router.dom

import react.*
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

