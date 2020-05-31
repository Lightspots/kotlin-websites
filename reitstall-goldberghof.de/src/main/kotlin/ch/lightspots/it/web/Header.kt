package ch.lightspots.it.web

import ch.lightspots.it.web.menu.LinkMenuItem
import ch.lightspots.it.web.menu.Logo
import ch.lightspots.it.web.menu.menu
import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.createElement
import react.dom.header

class Header : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    val values = Link.values().filter { it.headerEntry }
    header {
      menu(
          logo = Logo(createElement("img", ImageProps("logo", "images/logo/Logo.png"), null), Link.INDEX.href),
          menuItems = values.map { LinkMenuItem(it.displayName, it.href) }
      )
    }
  }
}

private data class ImageProps(val alt: String, val src: String): RProps

fun RBuilder.header() = child(Header::class) {}