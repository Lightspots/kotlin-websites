package ch.lightspots.it.web

import react.RBuilder
import react.RProps
import react.RPureComponent
import react.RState
import react.dom.a
import react.dom.header
import react.dom.img

class Header : RPureComponent<RProps, RState>() {
  override fun RBuilder.render() {
    val values = Links.values().filter { it.headerEntry }
    val middle = values.size / 2
    header("level") {
      menuItems(values, 0, middle)
      a(href = Links.INDEX.href, classes = "level-item has-text-centered") {
        img(alt = "logo", src = "images/logo/Logo.png") {}
      }
      menuItems(values, middle, values.size)
    }
  }

  private fun RBuilder.menuItems(links: List<Links>, from: Int, to: Int) {
    for (i in from until to) {
      val l = links[i]
      a(href = l.href, classes = "level-item has-text-centered") {
        +l.displayName
      }
    }
  }
}

fun RBuilder.header() = child(Header::class) {}