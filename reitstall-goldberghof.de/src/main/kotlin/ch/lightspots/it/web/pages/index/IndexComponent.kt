package ch.lightspots.it.web.pages.index

import ch.lightspots.it.web.shared.slider
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
        +"Wie der Name bereits verlauten lässt, liegt der ehemalige Kuhmilch-Bauernhof auf einem kleinen Berg im Süden Deutschlands."
      }
      p {
        +"Als Familienbetrieb geben wir unser Bestes, um Ihnen und Ihren Pferden das bestmögliche zu bieten."
      }
      p {
        +"Unser Hof setzt sich aus diversen unterschiedlichen Pferdeboxen, zwei Reitpätzen, eine Reithalle, sowie eine Longierhalle zusammen, welche hier ersichtlich sind:"
      }
      overviewMap()
    }
  }

}