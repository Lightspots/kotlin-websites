package ch.lightspots.it.web.pages.index

import ch.lightspots.it.web.slider.slider
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import react.dom.a
import react.dom.br
import react.dom.div
import react.dom.h1
import react.dom.p
import react.dom.video


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
      video {
        attrs {
          src = "https://cdn.lp42.ch/reitstall-goldberghof/videos/heuen_2017.mp4"
          controls = true
        }
        p {
          +"Ihr Browser kann dieses Video nicht wiedergeben"
          br {  }
          +"Sie können ihn unter"
          a("https://cdn.lp42.ch/reitstall-goldberghof/videos/heuen_2017.mp4") { +"https://cdn.lp42.ch/reitstall-goldberghof/videos/heuen_2017.mp4" }
          +"abrufen"
        }
      }
      p {
        +"Unser Hof setzt sich aus diversen unterschiedlichen Pferdeboxen, zwei Reitpätzen, einer Reithalle, sowie einer Longierhalle zusammen"
      }
      overviewMap()
    }
  }

}