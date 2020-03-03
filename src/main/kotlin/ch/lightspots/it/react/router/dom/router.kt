@file:JsModule("react-router-dom")

package ch.lightspots.it.react.router.dom

import react.Component
import react.RClass
import react.RProps
import react.RState
import react.ReactElement
import react.router.dom.RouteResultProps

@JsName("Route")
external class RouteComponent<T : RProps> : Component<RouteProps<T>, RState> {
  override fun render(): ReactElement?
}

external interface RouteProps<T : RProps> : RProps {
  var path: String?
  var exact: Boolean
  var strict: Boolean
  var component: RClass<RProps>
  var render: (props: RouteResultProps<T>) -> ReactElement?
}
