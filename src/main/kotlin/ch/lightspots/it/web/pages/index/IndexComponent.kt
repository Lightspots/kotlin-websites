package ch.lightspots.it.web.pages.index

import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.div
import react.dom.h1
import react.dom.p


val images = arrayOf(
    "images/slider/slider_image_01.jpg",
    "images/slider/slider_image_02.jpg",
    "images/slider/slider_image_03.jpg")

class IndexComponent : RComponent<RProps, RState>() {

  override fun RBuilder.render() {
    slider(images)
    div {
      h1("title is-1") {
        +"Reitstall Goldberghof"
      }
      p {
        +"Der Goldberghof liegt im Süden Deutschlands, wie der Name sagt auf einem kleinen Berg"
      }
      p {
        +"Wir bieten 2 Reitplätze, eine Reithalle sowie eine Longierhalle"
      }
      overviewMap()
    }
  }

}